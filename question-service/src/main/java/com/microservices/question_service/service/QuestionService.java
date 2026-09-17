package com.microservices.question_service.service;


import com.microservices.question_service.dao.QuestionDao;
import com.microservices.question_service.model.Question;
import com.microservices.question_service.model.QuestionWrapper;
import com.microservices.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategoty(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Failed to add", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<List<Question>> deleteQuestionById(int id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions) {

        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return  new ResponseEntity<>(questions, HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {

        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions){

            System.out.println("Question ID = " + question.getId());
            System.out.println("Question = " + question.getQuestion_title());
            QuestionWrapper wrapper = new QuestionWrapper();

            wrapper.setId(question.getId());
            wrapper.setQuestion_title(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            System.out.println("Wrapper ID = " + wrapper.getId());
            questionWrappers.add(wrapper);
        }


        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);

    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right= 0;
        for(Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRight_answer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
