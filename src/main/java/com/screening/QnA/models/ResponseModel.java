package com.screening.QnA.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ResponseModel {
    private Object result;
    private Object errorMessage;
    private HttpStatus status;
    private Date timeStamp = new Date();
}
