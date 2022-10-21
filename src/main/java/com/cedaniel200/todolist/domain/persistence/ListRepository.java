package com.cedaniel200.todolist.domain.persistence;

import com.cedaniel200.todolist.domain.model.ToDoList;

public interface ListRepository {
    ToDoList save(ToDoList toDoList);
}
