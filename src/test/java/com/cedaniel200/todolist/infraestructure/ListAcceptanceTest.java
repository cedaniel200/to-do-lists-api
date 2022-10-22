package com.cedaniel200.todolist.infraestructure;

import com.cedaniel200.todolist.domain.lists.ListMediator;
import com.cedaniel200.todolist.domain.lists.ListMediatorDefault;
import com.cedaniel200.todolist.domain.lists.ListValidator;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.infrastructure.controllers.ListsController;
import com.cedaniel200.todolist.infrastructure.exception.RestExceptionHandler;
import com.cedaniel200.todolist.infrastructure.model.ToDoListInfra;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListAcceptanceTest {

    @LocalServerPort
    private int port;
    @Autowired
    private RestExceptionHandler restExceptionHandler;
    @Mock
    private ListRepository repository;
    private ToDoListInfra toDoListInfra;

    @BeforeEach
    public void setup() {
        toDoListInfra = ToDoListInfra.builder()
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();

        ToDoList toDoListOut = ToDoList.builder()
                .id(100)
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();
        openMocks(this);
        when(repository.save(any(ToDoList.class))).thenReturn(toDoListOut);
        standaloneSetup(restExceptionHandler, new ListsController(new ListMediatorDefault(new ListValidator(), repository)));
    }

    @Test
    void shouldCreateAListAndReturnStatusCode200() {
        given()
                .contentType(ContentType.JSON)
                .body(toDoListInfra)
        .when()
                .post(String.format("http://localhost:%s/lists", port))
        .then()
                .statusCode(is(201))
                .body(containsString("100"))
                .body(containsString("date"));
    }

    @Test
    void shouldNotCreateAListAndReturnStatusCode400() {
        toDoListInfra.setName(null);
        toDoListInfra.setUser(null);

        given()
                .contentType(ContentType.JSON)
                .body(toDoListInfra)
        .when()
                .post(String.format("http://localhost:%s/lists", port))
        .then()
                .log().ifValidationFails()
                .statusCode(is(400))
                .body(containsString("Name is empty"))
                .body(containsString("User is empty"));
    }

}
