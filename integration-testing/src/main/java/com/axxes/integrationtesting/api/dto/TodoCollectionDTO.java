package com.axxes.integrationtesting.api.dto;

import java.util.List;

public class TodoCollectionDTO {

    private List<TodoDTO> todos;

    public TodoCollectionDTO(List<TodoDTO> todos) {
        this.todos = todos;
    }

    public List<TodoDTO> getTodos() {
        return todos;
    }
}
