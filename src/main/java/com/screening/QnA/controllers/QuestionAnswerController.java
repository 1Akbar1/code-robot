package com.screening.QnA.controllers;

import com.screening.QnA.models.NextQuestionRequest;
import com.screening.QnA.models.NextQuestionRequestModel;
import com.screening.QnA.models.ResponseModel;
import com.screening.QnA.services.QnAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/qna")
public class QuestionAnswerController {

    @Autowired
    public QnAService qnAService;

    @GetMapping(path = "/add")
    public ResponseEntity<ResponseModel> addQuestionAnswer() {
        ResponseModel response = mapResultToResponse(qnAService.fetchNSaveQuestions());
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @GetMapping(path = "/play")
    public ResponseEntity<ResponseModel> returnQuestionList(@RequestParam(required = false) Integer questionId) {
        ResponseModel response =  mapResultToResponse(qnAService.addQuestionAnswer(questionId));
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping(path = "/next",consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NextQuestionRequestModel> returnNextQuestion(@RequestBody NextQuestionRequest req ) {
        NextQuestionRequestModel nextQnA = qnAService.getNextQuestion(req);
        ResponseEntity rspEntity = new ResponseEntity<>(nextQnA, HttpStatus.OK);
        return rspEntity;
    }


    private ResponseModel mapResultToResponse(Map<String, Object> result) {
        ResponseModel res = new ResponseModel();
        if(result.containsKey("result") && !result.containsKey("err")) {
            res.setResult(result.get("result"));
            res.setStatus(HttpStatus.OK);
        } else {
            res.setErrorMessage(result.get("err"));
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }
}
