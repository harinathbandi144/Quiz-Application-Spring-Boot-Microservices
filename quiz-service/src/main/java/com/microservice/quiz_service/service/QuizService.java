package com.microservice.quiz_service.service;

import com.microservice.quiz_service.dao.QuizDao;
import com.microservice.quiz_service.feign.QuizInterface;
import com.microservice.quiz_service.model.QuestionWrapper;
import com.microservice.quiz_service.model.Quiz;
import com.microservice.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.generateQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionById(int id) {

        System.out.println("Quiz ID received: " + id);

        Quiz quiz = quizDao.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Quiz not found with id: " + id));

        System.out.println("Quiz found: " + quiz.getTitle());

        List<Integer> questionIds = quiz.getQuestionIds();

        System.out.println("Question IDs: " + questionIds);

        ResponseEntity<List<QuestionWrapper>> questions =
                quizInterface.getQuestionsFromId(questionIds);

        System.out.println("Response from Question Service: " + questions.getBody());

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
