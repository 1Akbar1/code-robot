package com.screening.QnA.servicesimpl;

import com.screening.QnA.entities.QuestionCategoryEntity;
import com.screening.QnA.entities.QuestionNAnswerEntity;
import com.screening.QnA.models.AddQuestionAnswerModel;
import com.screening.QnA.models.NextQuestionModel;
import com.screening.QnA.models.NextQuestionRequest;
import com.screening.QnA.models.NextQuestionRequestModel;
import com.screening.QnA.repositories.QuestionCategoryRepository;
import com.screening.QnA.repositories.QuestionNAnswerRepository;
import com.screening.QnA.services.QnAService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class QnAServiceImpl implements QnAService {

    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;

    @Autowired
    private QuestionNAnswerRepository questionNAnswerRepository;

    @Override
    public Map<String, Object> fetchNSaveQuestions() {
        Map<String, Object> result = new HashMap<>();

        try {
            this.questionCategoryRepository.saveAll(getFiveQnA());
            result.put("result","Questions and answers is saved successfully.");
        } catch (Exception e) {
            result.put("err",e.getMessage());
        }

        return result;
    }

    List<QuestionCategoryEntity> getFiveQnA() throws Exception
    {
        RestTemplate template = new RestTemplate();
        String url = "https://jservice.io/api/random/";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        int counter = 0;
        QuestionCategoryEntity qcEntity = null;
        QuestionNAnswerEntity qnaEntity = null;
        List<QuestionCategoryEntity> qcEntities = new ArrayList<>();
        List<QuestionNAnswerEntity> qnaEntities = null;

        while(counter < 5) {
            AddQuestionAnswerModel[] response =  template.exchange(url, HttpMethod.GET, entity, AddQuestionAnswerModel[].class).getBody();
            for(AddQuestionAnswerModel a : response) {
                if (a.getCategory_id() != null) {
                    qcEntity = this.questionCategoryRepository.findById(a.getCategory_id()).orElse(null);
                }

                if(qcEntity==null) {
                    qcEntity = new QuestionCategoryEntity();
                    qcEntity.setId(a.getCategory().getId());
                    qcEntity.setTitle(a.getCategory().getTitle());
                    qcEntity.setClues_count(a.getCategory().getClues_count());
                    qcEntity.setCreated_at(a.getCategory().getCreated_at());
                    qcEntity.setUpdated_at(a.getCategory().getUpdated_at());
                }

                qnaEntities = qcEntity.getQaEntities();
                if(qnaEntities.stream().noneMatch(obj -> obj.getId() == a.getId())) {
                    qnaEntity = new QuestionNAnswerEntity();
                    qnaEntity.setId(a.getId());
                    qnaEntity.setQuestion(a.getQuestion());
                    qnaEntity.setAnswer(a.getAnswer());
                    qnaEntity.setValue(a.getValue());
                    qnaEntity.setGame_id(a.getGame_id());
                    qnaEntity.setQcEntity(qcEntity);
                    qnaEntity.setAirdate(a.getAirdate());
                    qnaEntity.setCreated_at(a.getCreated_at());
                    qnaEntity.setUpdated_at(a.getUpdated_at());
                    qnaEntities.add(qnaEntity);
                    qcEntities.add(qcEntity);
                    counter++;
                }
            }
        }

        return qcEntities;
    }

    @Override
    public Map<String, Object> addQuestionAnswer(Integer questionId) {
        Map<String, Object> result = new HashMap<>();

        try {
            if(questionId!=null) {
                Optional<QuestionNAnswerEntity> question = this.questionNAnswerRepository.findById(questionId);
                result.put("result", question.map(Collections::singletonList).orElse(Collections.emptyList()));
            } else {
                List<QuestionNAnswerEntity> questions = this.questionNAnswerRepository.findAll();
                result.put("result", questions);
            }

        } catch (Exception e) {
            result.put("err", "Failed to fetch questions list.");
        }

        return result;
    }

    @Override
    public NextQuestionRequestModel getNextQuestion(NextQuestionRequest questionRequest) {

        NextQuestionRequestModel nextQnA = new NextQuestionRequestModel();
        int counter = 0;

        try {
            if(questionRequest!=null) {
                List<QuestionNAnswerEntity> questions = this.questionNAnswerRepository.findAll();
                List<QuestionNAnswerEntity> sortedQnAList = questions.stream().sorted((e1,e2)-> e1.getId().compareTo(e2.getId())).collect(Collectors.toList());
                for(QuestionNAnswerEntity entity : sortedQnAList)
                {

                     if(counter == 1)
                     {
                         NextQuestionModel nxtModel = new NextQuestionModel();
                         nxtModel.setQuestion(entity.getQuestion());
                         nxtModel.setQuestionId(entity.getId());
                         nextQnA.setNext_question(nxtModel);
                         break;
                     }
                    if(entity.getId().equals(questionRequest.getQuestion_id()))
                    {
                        nextQnA.setCorrect_answer(entity.getAnswer());

                        counter = 1;
                    }
                }

            }

        } catch (Exception e) {
            return nextQnA;
        }

        return nextQnA;
    }
}
