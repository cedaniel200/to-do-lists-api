package com.cedaniel200.todolist.infrastructure.controllers;

import com.cedaniel200.todolist.domain.lists.ListMediator;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.infrastructure.mappers.ToDoListMapper;
import com.cedaniel200.todolist.infrastructure.model.ListCollection;
import com.cedaniel200.todolist.infrastructure.model.ToDoListInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class ListsController {

    @Autowired
    private final ListMediator listMediator;

    public ListsController(ListMediator listMediator) {
        this.listMediator = listMediator;
    }

    @PostMapping(path = "/lists")
    public ResponseEntity<ToDoListInfra> create(@RequestBody @Valid ToDoListInfra toDoListInfra) {
        ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListInfra);
        ToDoList toDoListCreated = listMediator.create(toDoListToCreate);
        ToDoListInfra toDoListInfraCreated = ToDoListMapper.toToDoListInfra(toDoListCreated);
        return new ResponseEntity<>(toDoListInfraCreated, HttpStatus.CREATED);
    }

    @GetMapping(path = "/lists/{listId}")
    public ResponseEntity<ToDoListInfra> getById(@PathVariable("listId") Long listId) {
        ToDoList toDoListFinded = listMediator.getListById(listId);
        ToDoListInfra toDoListInfraFinded = ToDoListMapper.toToDoListInfra(toDoListFinded);
        return new ResponseEntity<>(toDoListInfraFinded, HttpStatus.OK);
    }

    @GetMapping(path = "/lists")
    public ResponseEntity<ListCollection> getById() {
        List<ToDoList> toDoListsFinded = listMediator.getAllLists();
        List<ToDoListInfra> toDoListsInfraFinded = toDoListsFinded.stream()
                .map(ToDoListMapper::toToDoListInfra)
                .collect(toList());
        return new ResponseEntity<>(new ListCollection(toDoListsInfraFinded), HttpStatus.OK);
    }

}
