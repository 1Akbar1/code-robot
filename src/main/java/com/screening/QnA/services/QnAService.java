package com.screening.QnA.services;

import com.screening.QnA.models.NextQuestionModel;
import com.screening.QnA.models.NextQuestionRequest;
import com.screening.QnA.models.NextQuestionRequestModel;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface QnAService {

    public Map<String, Object> fetchNSaveQuestions();

    public Map<String, Object> addQuestionAnswer(Integer questionId);
    public NextQuestionRequestModel getNextQuestion(NextQuestionRequest qRequest);
}
