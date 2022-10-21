package com.cedaniel200.todolist.infrastructure.mappers;

import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.infrastructure.model.ToDoListInfra;

import java.util.Calendar;

public class ToDoListMapper {

    public static ToDoList toToDoList(ToDoListInfra toDoListInfra){
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListInfra.getId());
        toDoList.setName(toDoListInfra.getName());
        toDoList.setDescription(toDoListInfra.getDescription());
        toDoList.setUser(toDoListInfra.getUser());
        return toDoList;
    }

    public static ToDoListInfra ToDoListInfra(ToDoList toDoList){
        ToDoListInfra toDoListInfra = new ToDoListInfra();
        toDoListInfra.setId(toDoList.getId());
        toDoListInfra.setName(toDoList.getName());
        toDoListInfra.setDescription(toDoList.getDescription());
        toDoListInfra.setUser(toDoList.getUser());
        toDoListInfra.setDate(Calendar.getInstance().getTime());
        return toDoListInfra;
    }
}
