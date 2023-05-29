package com.screening.QnA.repositories;

import com.screening.QnA.entities.QuestionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategoryEntity, Integer> {
}
