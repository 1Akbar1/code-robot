package com.screening.QnA.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.screening.QnA.utils.DateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "questions_category_master")
public class QuestionCategoryEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

//    @JsonSerialize(using = DateSerializer.Serialize.class)
//    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    @Column(name = "created_at")
    private Date created_at;
    @JsonSerialize(using = DateSerializer.Serialize.class)
    @JsonDeserialize(using = DateSerializer.Deserialize.class)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "clues_count")
    private Integer clues_count;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="qcEntity", fetch = FetchType.EAGER)
    private List<QuestionNAnswerEntity> qaEntities = new ArrayList<>();

}
