package com.axxes.integrationtesting.service.domain;

import java.util.Objects;
import java.util.UUID;

public class Todo {

    private final UUID id;
    private final String summary;
    private final TodoStatus status;

    public Todo(UUID id, String summary, TodoStatus status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (!Objects.equals(id, todo.id)) return false;
        if (!Objects.equals(summary, todo.summary)) return false;
        return status == todo.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
