package com.screening.QnA.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class NextQuestionModel {

    private int questionId;
    private String question;

}
