package com.screening.QnA.repositories;

import com.screening.QnA.entities.QuestionNAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionNAnswerRepository extends JpaRepository<QuestionNAnswerEntity, Integer> {
}
