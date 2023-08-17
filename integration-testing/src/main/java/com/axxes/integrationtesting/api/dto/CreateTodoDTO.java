package com.axxes.integrationtesting.api.dto;

public class CreateTodoDTO {

    private String summary;

    public CreateTodoDTO() {
    }

    public CreateTodoDTO(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }
}
