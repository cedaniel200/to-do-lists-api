package com.cedaniel200.todolist.infraestructure;

import com.cedaniel200.todolist.domain.lists.ListMediatorDefault;
import com.cedaniel200.todolist.domain.lists.ListValidator;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.infrastructure.controllers.ListsController;
import com.cedaniel200.todolist.infrastructure.exception.RestExceptionHandler;
import com.cedaniel200.todolist.infrastructure.model.ToDoListDTO;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Acceptance Test of List - API")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListAcceptanceTest {

    //TODO : Se deben mejorar los test para validar el contrato de los endpoints Por el momento solo se valida el codigo de estado y que contenga alguna información

    @SuppressWarnings("unused")
    @LocalServerPort
    private int port;
    @SuppressWarnings("unused")
    @Autowired
    private RestExceptionHandler restExceptionHandler;
    @Mock
    private ListRepository repository;
    private ToDoListDTO toDoListDTO;

    @BeforeEach
    public void setup() {
        toDoListDTO = ToDoListDTO.builder()
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
        ArrayList<ToDoList> lists = new ArrayList<>();
        lists.add(new ToDoList());
        lists.add(new ToDoList());
        lists.add(new ToDoList());
        openMocks(this);
        when(repository.save(any(ToDoList.class))).thenReturn(toDoListOut);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(toDoListOut));
        when(repository.existsById(any(Long.class))).thenReturn(true);
        doNothing().when(repository).delete(any(Long.class));
        when(repository.findAll()).thenReturn(lists);
        standaloneSetup(restExceptionHandler, new ListsController(new ListMediatorDefault(new ListValidator(), repository)));
    }

    @Nested
    @DisplayName("POST - /lists")
    class WhenCreateAList {
        @Test
        void shouldCreateAListAndReturnStatusCode201() {
            given()
                    .contentType(ContentType.JSON)
                    .body(toDoListDTO)
                    .when()
                    .post(String.format("http://localhost:%s/lists", port))
                    .then()
                    .statusCode(is(201))
                    .body(containsString("100"))
                    .body(containsString("date"));
        }

        @Test
        void shouldNotCreateAListAndReturnStatusCode400() {
            toDoListDTO.setName(null);
            toDoListDTO.setUser(null);

            given()
                    .contentType(ContentType.JSON)
                    .body(toDoListDTO)
                    .when()
                    .post(String.format("http://localhost:%s/lists", port))
                    .then()
                    .log().ifValidationFails()
                    .statusCode(is(400))
                    .body(containsString("Name is empty"))
                    .body(containsString("User is empty"));
        }
    }

    @Nested
    @DisplayName("GET - /lists/{listId}")
    class WhenGetAListById {
        @Test
        void shouldGetAListAndReturnStatusCode200() {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(String.format("http://localhost:%s/lists/1", port))
                    .then()
                    .statusCode(is(200))
                    .body(containsString("100"))
                    .body(containsString("date"));
        }

        @Test
        void shouldNotGetAListAndReturnStatusCode404() {
            when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(String.format("http://localhost:%s/lists/0", port))
                    .then()
                    .log().ifValidationFails()
                    .statusCode(is(404))
                    .body(containsString("NOT_FOUND"));
        }
    }

    @Nested
    @DisplayName("GET - /lists/")
    class WhenGetAllLists {
        @Test
        void shouldGetAListCollectionAndReturnStatusCode200() {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(String.format("http://localhost:%s/lists", port))
                    .then()
                    .statusCode(is(200))
                    .body(containsString("\"size\":3"));
        }

    }

    @Nested
    @DisplayName("DELETE - /lists/{listId}")
    class WhenDeleteAList {
        @Test
        void shouldDeleteAListAndReturnStatusCode200() {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(String.format("http://localhost:%s/lists/1", port))
                    .then()
                    .statusCode(is(200));
            verify(repository).delete(any(Long.class));
        }

        @Test
        void shouldNotDeleteAListAndReturnStatusCode404() {
            when(repository.existsById(any(Long.class))).thenReturn(false);

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(String.format("http://localhost:%s/lists/0", port))
                    .then()
                    .log().ifValidationFails()
                    .statusCode(is(404))
                    .body(containsString("NOT_FOUND"));
            verify(repository, times(0)).delete(any(Long.class));
        }
    }

    @Nested
    @DisplayName("PATCH - /lists")
    class WhenUpdateAList {
        @Test
        void shouldDeleteAListAndReturnStatusCode200() {
            toDoListDTO.setId(1);

            given()
                    .contentType(ContentType.JSON)
                    .body(toDoListDTO)
                    .when()
                    .patch(String.format("http://localhost:%s/lists", port))
                    .then()
                    .statusCode(is(200));
            verify(repository).update(any(ToDoList.class));
        }

        @Test
        void shouldNotDeleteAListAndReturnStatusCode404() {
            toDoListDTO.setId(1);
            when(repository.existsById(any(Long.class))).thenReturn(false);

            given()
                    .contentType(ContentType.JSON)
                    .body(toDoListDTO)
                    .when()
                    .patch(String.format("http://localhost:%s/lists", port))
                    .then()
                    .log().ifValidationFails()
                    .statusCode(is(404))
                    .body(containsString("NOT_FOUND"));
            verify(repository, times(0)).update(any(ToDoList.class));
        }
    }

}
