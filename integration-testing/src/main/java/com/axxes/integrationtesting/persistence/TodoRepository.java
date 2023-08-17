package com.axxes.integrationtesting.persistence;

import com.axxes.integrationtesting.persistence.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    @Query("SELECT t from TodoEntity t ORDER BY t.summary ASC")
    List<TodoEntity> findAllTodos();

}
