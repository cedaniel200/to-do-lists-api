package com.cedaniel200.todolist.infrastructure.controllers;

import com.cedaniel200.todolist.domain.lists.ListMediator;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.infrastructure.mappers.ToDoListMapper;
import com.cedaniel200.todolist.infrastructure.model.ListCollection;
import com.cedaniel200.todolist.infrastructure.model.ToDoListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ToDoListDTO> create(@RequestBody ToDoListDTO toDoListDTO) {
        ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListDTO);
        ToDoList toDoListCreated = listMediator.create(toDoListToCreate);
        ToDoListDTO toDoListDTOCreated = ToDoListMapper.toToDoListDTO(toDoListCreated);
        return new ResponseEntity<>(toDoListDTOCreated, HttpStatus.CREATED);
    }

    @GetMapping(path = "/lists/{listId}")
    public ResponseEntity<ToDoListDTO> getById(@PathVariable("listId") long listId) {
        ToDoList toDoListFinded = listMediator.getListById(listId);
        ToDoListDTO toDoListDTOFinded = ToDoListMapper.toToDoListDTO(toDoListFinded);
        return new ResponseEntity<>(toDoListDTOFinded, HttpStatus.OK);
    }

    @GetMapping(path = "/lists")
    public ResponseEntity<ListCollection> getById() {
        List<ToDoList> toDoListsFinded = listMediator.getAllLists();
        List<ToDoListDTO> toDoListsInfraFinded = toDoListsFinded.stream()
                .map(ToDoListMapper::toToDoListDTO)
                .collect(toList());
        return new ResponseEntity<>(new ListCollection(toDoListsInfraFinded), HttpStatus.OK);
    }

    @DeleteMapping(path = "/lists/{listId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("listId") long listId) {
        listMediator.delete(listId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/lists")
    public ResponseEntity<HttpStatus> update(@RequestBody ToDoListDTO toDoListDTO) {
        ToDoList toDoListToUpdate = ToDoListMapper.toToDoList(toDoListDTO);
        listMediator.update(toDoListToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
