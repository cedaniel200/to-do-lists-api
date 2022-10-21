package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.model.ToDoList;

public interface ListCreator {
    ToDoList create(ToDoList toDoList);
}
