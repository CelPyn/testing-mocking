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
    void when_gettingExistingTodo_todoIsReturned() {
        when()
                .get("/todo/{id}", "eed612a1-4f45-4fb1-8707-c3f995422112")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo("eed612a1-4f45-4fb1-8707-c3f995422112"))
                .body("summary", equalTo("Deploy application"))
                .body("status", equalTo("TODO"));
    }

    @Test
    @DataSet(value = "initialTodos.yml", cleanAfter = true)
    void when_gettingNonExistingTodo_404_isReturned() {
        when()
                .get("/todo/{id}", UUID.randomUUID())
                .then()
                .assertThat()
                .statusCode(404);
    }

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

    @Test
    @DataSet(value = "initialTodos.yml", cleanAfter = true)
    void when_creatingTodo_todoIsCreated() {
        when()
                .get("/todo")
                .then()
                .assertThat()
                .statusCode(200)
                .body("todos", hasSize(3));

        UUID newId = with().body(new CreateTodoDTO("Become a millionaire")).header(new Header("Content-Type", "application/json"))
                .when()
                .post("/todo")
                .then()
                .assertThat()
                .statusCode(201)
                .body("id", not(blankString()))
                .body("summary", equalTo("Become a millionaire"))
                .body("status", equalTo("TODO"))
                .extract().body().as(TodoDTO.class).getId();

        when()
                .get("/todo/{id}", newId)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DataSet(value = "initialTodos.yml", cleanAfter = true)
    void when_markingTodoAsDone_todoIsDone() {
        when()
                .put("/todo/{id}", "db22362a-059b-4e7e-bdeb-8c25e0d49808")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo("db22362a-059b-4e7e-bdeb-8c25e0d49808"))
                .body("summary", equalTo("Figure out how to set up Kafka"))
                .body("status", equalTo("DONE"));
    }

    @Test
    @DataSet(value = "initialTodos.yml", cleanAfter = true)
    void when_deletingTodo_todoIsRemoved() {
        when()
                .delete("/todo/{id}", "db22362a-059b-4e7e-bdeb-8c25e0d49808")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo("db22362a-059b-4e7e-bdeb-8c25e0d49808"))
                .body("summary", equalTo("Figure out how to set up Kafka"))
                .body("status", equalTo("TODO"));
    }
}
