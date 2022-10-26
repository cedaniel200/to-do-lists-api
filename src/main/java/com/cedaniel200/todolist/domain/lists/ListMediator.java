package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.model.ToDoList;

import java.util.List;

public interface ListMediator {
    ToDoList create(ToDoList toDoList);

    ToDoList getListById(Long listId);

    List<ToDoList> getAllLists();
}
