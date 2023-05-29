package com.screening.QnA.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
public class NextQuestionRequest {

    private int question_id;
    private String answer;
}
