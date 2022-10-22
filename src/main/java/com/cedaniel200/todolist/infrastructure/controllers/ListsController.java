package com.cedaniel200.todolist.infrastructure.controllers;

import com.cedaniel200.todolist.domain.lists.ListMediator;
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
    private final ListMediator listMediator;

    public ListsController(ListMediator listMediator) {
        this.listMediator = listMediator;
    }

    @PostMapping(path = "/lists")
    public ResponseEntity<?>  create(@RequestBody ToDoListInfra toDoListInfra){
        try {
            ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListInfra);
            ToDoList toDoListCreated = listMediator.create(toDoListToCreate);
            ToDoListInfra toDoListInfraCreated = ToDoListMapper.toToDoListInfra(toDoListCreated);
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
