package com.cedaniel200.todolist.domain.persistence;

import com.cedaniel200.todolist.domain.model.ToDoList;

import java.util.List;
import java.util.Optional;

public interface ListRepository {
    ToDoList save(ToDoList toDoList);

    Optional<ToDoList> findById(long listId);

    List<ToDoList> findAll();
}
