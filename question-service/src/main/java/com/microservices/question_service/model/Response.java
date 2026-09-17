package com.microservices.question_service.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Response {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Response(int id, String response) {
        this.id = id;
        this.response = response;
    }

    public Response() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private int id;
    private String response;
}
