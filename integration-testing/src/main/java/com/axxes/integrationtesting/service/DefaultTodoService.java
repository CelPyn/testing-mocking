package com.axxes.integrationtesting.service;

import com.axxes.integrationtesting.persistence.TodoRepository;
import com.axxes.integrationtesting.persistence.entity.TodoEntity;
import com.axxes.integrationtesting.service.domain.Todo;
import com.axxes.integrationtesting.service.domain.TodoStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultTodoService implements TodoService {

    private final TodoRepository repository;

    public DefaultTodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Todo> getTodo(UUID id) {
        return repository.findById(id.toString()).map(entity -> new Todo(entity.getId(), entity.getSummary(), entity.getStatus()));
    }

    @Override
    public List<Todo> getAllTodos() {
        return repository.findAllTodos()
                .stream()
                .map(entity -> new Todo(entity.getId(), entity.getSummary(), entity.getStatus()))
                .toList();
    }

    @Override
    @Transactional
    public Todo createTodo(String summary) {
        TodoEntity entity = new TodoEntity(UUID.randomUUID(), summary);

        repository.save(entity);

        return new Todo(entity.getId(), entity.getSummary(), entity.getStatus());
    }

    @Override
    @Transactional
    public Todo markAsDone(UUID id) {
        Optional<TodoEntity> result = repository.findById(id.toString());
        TodoEntity entity = result.orElseThrow();

        entity.setStatus(TodoStatus.DONE);

        return new Todo(entity.getId(), entity.getSummary(), entity.getStatus());
    }

    @Override
    @Transactional
    public Todo deleteTodo(UUID id) {
        Optional<TodoEntity> result = repository.findById(id.toString());
        TodoEntity entity = result.orElseThrow();
        Todo todo = new Todo(entity.getId(), entity.getSummary(), entity.getStatus());

        repository.deleteById(id.toString());

        return todo;
    }
}
