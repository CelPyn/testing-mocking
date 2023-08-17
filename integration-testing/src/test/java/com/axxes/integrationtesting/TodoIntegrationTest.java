package com.axxes.integrationtesting;

import com.axxes.integrationtesting.api.dto.CreateTodoDTO;
import com.axxes.integrationtesting.api.dto.TodoDTO;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Testcontainers
@DBRider
class TodoIntegrationTest {

    @Container
    @ServiceConnection
    static MariaDBContainer<?> DATABASE = new MariaDBContainer<>("mariadb");

    @Test
    @DataSet(value = "initialTodos.yml", cleanAfter = true)
    void when_gettingAllTodos_todosAreReturned_todosAreSortedBySummary() {
        when()
                .get("/todo")
                .then()
                .assertThat()
                .statusCode(200)
                .body("todos", hasSize(3))
                .body("todos[0].id", not(blankString()))
                .body("todos[0].summary", equalTo("Add unit tests"))
                .body("todos[0].status", equalTo("DONE"));
    }

}
