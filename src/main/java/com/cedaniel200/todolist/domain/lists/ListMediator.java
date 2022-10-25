package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.model.ToDoList;

public interface ListMediator {
    ToDoList create(ToDoList toDoList);

    ToDoList getListById(Long listId);
}
