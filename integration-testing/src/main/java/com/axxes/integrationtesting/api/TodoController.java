package com.axxes.integrationtesting.api;

import com.axxes.integrationtesting.api.dto.CreateTodoDTO;
import com.axxes.integrationtesting.api.dto.TodoCollectionDTO;
import com.axxes.integrationtesting.api.dto.TodoDTO;
import com.axxes.integrationtesting.service.TodoService;
import com.axxes.integrationtesting.service.domain.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable UUID id) {
        Optional<Todo> result = todoService.getTodo(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TodoDTO todo = result.map(TodoController::map).get();
        return ResponseEntity.ok(todo);
    }

    @GetMapping("/todo")
    public TodoCollectionDTO getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos()
                .stream()
                .map(TodoController::map)
                .toList();
        return new TodoCollectionDTO(todos);
    }

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDTO createTodo(@RequestBody CreateTodoDTO createTodoDTO) {
        Todo todo = todoService.createTodo(createTodoDTO.getSummary());
        return map(todo);
    }

    @PutMapping("/todo/{id}")
    public TodoDTO markAsDone(@PathVariable UUID id) {
        Todo todo = todoService.markAsDone(id);
        return map(todo);
    }

    @DeleteMapping("/todo/{id}")
    public TodoDTO deleteTodo(@PathVariable UUID id) {
        Todo todo = todoService.deleteTodo(id);
        return map(todo);
    }

    private static TodoDTO map(Todo todo) {
        return new TodoDTO(todo.getId(), todo.getSummary(), todo.getStatus());
    }
}
