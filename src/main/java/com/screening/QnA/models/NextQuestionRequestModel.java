package com.screening.QnA.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class NextQuestionRequestModel {

    private String correct_answer;
    private NextQuestionModel next_question;
}
