package com.microservices.question_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
    private String difficultylevel;

    public void setId(int id) {
        Id = id;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public void setDifficultylevel(String difficultylevel) {
        this.difficultylevel = difficultylevel;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public int getId() {
        return Id;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public String getDifficultylevel() {
        return difficultylevel;
    }

    public String getCategory() {
        return category;
    }
}
