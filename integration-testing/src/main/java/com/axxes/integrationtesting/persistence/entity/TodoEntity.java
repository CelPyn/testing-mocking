package com.axxes.integrationtesting.persistence.entity;

import com.axxes.integrationtesting.service.domain.TodoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TODOS")
public class TodoEntity {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "SUMMARY", nullable = false)
    private String summary;
    @Column(name = "STATUS", nullable = false)
    private String status;

    public TodoEntity() {
    }

    public TodoEntity(UUID id, String summary) {
        this.id = id.toString();
        this.summary = summary;
        this.status = TodoStatus.TODO.name();
    }

    public TodoEntity(UUID id, String summary, String status) {
        this.id = id.toString();
        this.summary = summary;
        this.status = status;
    }

    public UUID getId() {
        return UUID.fromString(id);
    }

    public void setId(UUID id) {
        this.id = id.toString();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public TodoStatus getStatus() {
        return TodoStatus.valueOf(status);
    }

    public void setStatus(TodoStatus status) {
        this.status = status.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoEntity that = (TodoEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(summary, that.summary)) return false;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
