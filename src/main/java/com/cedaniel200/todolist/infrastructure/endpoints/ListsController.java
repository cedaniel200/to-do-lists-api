package com.cedaniel200.todolist.infrastructure.endpoints;

import com.cedaniel200.todolist.domain.lists.ListCreator;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.infrastructure.mappers.ToDoListMapper;
import com.cedaniel200.todolist.infrastructure.model.ToDoListInfra;
import com.cedaniel200.todolist.infrastructure.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListsController {

    @Autowired
    private final ListCreator listCreator;

    public ListsController(ListCreator listCreator) {
        this.listCreator = listCreator;
    }

    @PostMapping(path = "/lists")
    public ResponseEntity<?>  create(@RequestBody ToDoListInfra toDoListInfra){
        try {
            ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListInfra);
            ToDoList toDoListCreated = listCreator.create(toDoListToCreate);
            ToDoListInfra toDoListInfraCreated = ToDoListMapper.ToDoListInfra(toDoListCreated);
            return new ResponseEntity(toDoListInfraCreated, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(new Error("Solicitud errada", e.getMessage().split(System.lineSeparator())),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(new Error("Error inesperado", new String[]{e.getMessage()}),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
