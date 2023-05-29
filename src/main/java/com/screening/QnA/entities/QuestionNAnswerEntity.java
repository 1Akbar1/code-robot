package com.screening.QnA.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.screening.QnA.utils.DateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "question_answer_master")
public class QuestionNAnswerEntity {

    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "value")
    private Integer value;
    @JsonSerialize(using = DateSerializer.Serialize.class)
    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    @Column(name = "airdate")
    private Date airdate;
    @JsonSerialize(using = DateSerializer.Serialize.class)
    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    @Column(name = "created_at")
    private Date created_at;
    @JsonSerialize(using = DateSerializer.Serialize.class)
    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "game_id")
    private Integer game_id;

    @Column(name = "invalid_count")
    private Integer invalid_count;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private QuestionCategoryEntity qcEntity;

}
