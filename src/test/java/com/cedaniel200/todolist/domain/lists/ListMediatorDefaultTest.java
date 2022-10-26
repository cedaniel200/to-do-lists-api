package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.exception.NotFoundException;
import com.cedaniel200.todolist.domain.exception.ValidationException;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Unit Tests of List Mediator")
class ListMediatorDefaultTest {

    @Mock
    private ListRepository repository;
    private ListMediator mediator;
    private ToDoList toDoListIn;

    @BeforeEach
    public void setup() {
        toDoListIn = ToDoList.builder()
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
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(toDoListOut));
        mediator = new ListMediatorDefault(new ListValidator(), repository);
    }

    @Nested
    @DisplayName("Tests When Create a List")
    class WhenCreateAList {

        @Test
        void shouldCreateListSuccessful() {
            ToDoList listCreated = mediator.create(toDoListIn);

            assertEquals(100, listCreated.getId());
            verify(repository).save(any(ToDoList.class));
        }

        @Test
        void shouldThrowValidationExceptionWhenNameIsNull() {
            toDoListIn.setName(null);

            ValidationException exception = assertThrows(ValidationException.class,
                    () -> mediator.create(toDoListIn),
                    "Se esperaba un ValidationException cuando Name es nulo, pero no fue lanzada");
            assertEquals(1, exception.getErrors().size());
            assertEquals("ToDoList", exception.getObject());
            assertTrue(exception.getErrors().get(0).getTitle().contains("Name"));
            assertTrue(exception.getErrors().get(0).getDetail().contains("Name is empty"));
            verify(repository, times(0)).save(any(ToDoList.class));
        }

        @Test
        void shouldThrowValidationExceptionWhenNameIsEmpty() {
            toDoListIn.setName("");

            ValidationException exception = assertThrows(ValidationException.class,
                    () -> mediator.create(toDoListIn),
                    "Se esperaba un ValidationException cuando Name es vacio, pero no fue lanzada");
            assertEquals(1, exception.getErrors().size());
            assertTrue(exception.getErrors().get(0).getTitle().contains("Name"));
            assertTrue(exception.getErrors().get(0).getDetail().contains("Name is empty"));
            verify(repository, times(0)).save(any(ToDoList.class));
        }

        @Test
        void shouldThrowValidationExceptionWhenUserIsNull() {
            toDoListIn.setUser(null);

            ValidationException exception = assertThrows(ValidationException.class,
                    () -> mediator.create(toDoListIn),
                    "Se esperaba un ValidationException cuando User es nulo, pero no fue lanzada");
            assertEquals(1, exception.getErrors().size());
            assertEquals("ToDoList", exception.getObject());
            assertTrue(exception.getErrors().get(0).getTitle().contains("User"));
            assertTrue(exception.getErrors().get(0).getDetail().contains("User is empty"));
            verify(repository, times(0)).save(any(ToDoList.class));
        }

        @Test
        void shouldThrowValidationExceptionWhenUserIsEmpty() {
            toDoListIn.setUser("");

            ValidationException exception = assertThrows(ValidationException.class,
                    () -> mediator.create(toDoListIn),
                    "Se esperaba un ValidationException cuando User es vacio, pero no fue lanzada");
            assertEquals(1, exception.getErrors().size());
            assertEquals("ToDoList", exception.getObject());
            assertTrue(exception.getErrors().get(0).getTitle().contains("User"));
            assertTrue(exception.getErrors().get(0).getDetail().contains("User is empty"));
            verify(repository, times(0)).save(any(ToDoList.class));
        }

        @Test
        void shouldThrowValidationExceptionWhenUserFormtIsNotCorrect() {
            toDoListIn.setUser("cdanielmg200@");

            ValidationException exception = assertThrows(ValidationException.class,
                    () -> mediator.create(toDoListIn),
                    "Se esperaba un ValidationException cuando User tiene un formado errado, pero no fue lanzada");
            assertEquals(1, exception.getErrors().size());
            assertEquals("ToDoList", exception.getObject());
            assertTrue(exception.getErrors().get(0).getTitle().contains("User"));
            assertTrue(exception.getErrors().get(0).getDetail().contains("The user does not have the email format"));
            verify(repository, times(0)).save(any(ToDoList.class));
        }

    }

    @Nested
    @DisplayName("Tests When Get a List By Id")
    class WhenGetListBy {

        @Test
        void mustReturnListWhenExists(){
            ToDoList list = mediator.getListById(1L);

            assertEquals(100, list.getId());
            verify(repository).findById(any(Long.class));
        }


        @Test
        void shouldThrowNotFoundExceptionWhenIdDoesntExists(){
            long id = 1L;
            when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

            NotFoundException exception = assertThrows(NotFoundException.class,
                    () -> mediator.getListById(id),
                    "Se esperaba un NotFoundException cuando el id no existe, pero no fue lanzada");

            assertTrue(exception.getMessage().contains(String.format("list with id %s not found", id)));
        }
    }

}
