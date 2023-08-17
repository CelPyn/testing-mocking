package com.axxes.integrationtesting.service;

import com.axxes.integrationtesting.service.domain.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoService {

    Optional<Todo> getTodo(UUID id);

    List<Todo> getAllTodos();

    Todo createTodo(String summary);

    Todo markAsDone(UUID id);

    Todo deleteTodo(UUID id);

}
