package com.screening.QnA.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.screening.QnA.utils.DateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddQuestionAnswerModel {
    private Integer id;
    private String answer;
    private String question;
    private Integer value;
    @JsonSerialize(using = DateSerializer.Serialize.class)
    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    private Date airdate;
    @JsonSerialize(using = DateSerializer.Serialize.class)
    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    private Date created_at;
    @JsonSerialize(using = DateSerializer.Serialize.class)
    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    private Date updated_at;
    private QuestionCategoryModel category;
    private Integer category_id;
    private Integer game_id;
    private Integer invalid_count;
}
