package com.axxes.integrationtesting.api.dto;

import com.axxes.integrationtesting.service.domain.TodoStatus;

import java.util.UUID;

public class TodoDTO {

    private UUID id;
    private String summary;
    private TodoStatus status;

    public TodoDTO(UUID id, String summary, TodoStatus status) {
        this.id = id;
        this.summary = summary;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public TodoStatus getStatus() {
        return status;
    }
}
