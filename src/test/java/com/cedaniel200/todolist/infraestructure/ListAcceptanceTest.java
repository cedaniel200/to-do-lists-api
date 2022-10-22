package com.cedaniel200.todolist.infraestructure;

import com.cedaniel200.todolist.domain.lists.ListMediatorDefault;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.infrastructure.controllers.ListsController;
import com.cedaniel200.todolist.infrastructure.model.ToDoListInfra;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListAcceptanceTest {

    @LocalServerPort
    private int port;
    @Mock
    private ListRepository repository;
    // @InjectMocks private ListsController controller;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup(){
        ToDoList toDoListOut = ToDoList.builder()
                .id(100)
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();
        openMocks(this);
        when(repository.save(any(ToDoList.class))).thenReturn(toDoListOut);
        webAppContextSetup(context);
        standaloneSetup(new ListsController(new ListMediatorDefault(repository)));
    }

    @Test
    void shouldCreateAListAndReturnStatusCode200(){
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name("Cosas por hacer")
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();

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
    void shouldNotCreateAListAndReturnStatusCode400(){
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name(null)
                .description("Mis cosas por hacer esta semana")
                .user("cdanielmg200@gmail.com")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(toDoListInfra)
        .when()
                .post(String.format("http://localhost:%s/lists", port))
        .then()
                .statusCode(is(400))
                .body(containsString("Name is empty"));
    }

}
