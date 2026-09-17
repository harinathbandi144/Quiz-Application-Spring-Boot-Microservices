package com.microservice.quiz_service.dao;

import com.microservice.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
