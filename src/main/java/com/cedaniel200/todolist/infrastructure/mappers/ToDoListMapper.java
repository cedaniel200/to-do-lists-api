package com.cedaniel200.todolist.infrastructure.mappers;

import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.infrastructure.model.ToDoListInfra;

import java.util.Calendar;

public class ToDoListMapper {

    private ToDoListMapper() {
    }

    public static ToDoList toToDoList(ToDoListInfra toDoListInfra){
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListInfra.getId());
        toDoList.setName(toDoListInfra.getName());
        toDoList.setDescription(toDoListInfra.getDescription());
        toDoList.setUser(toDoListInfra.getUser());
        return toDoList;
    }

    public static ToDoListInfra toToDoListInfra(ToDoList toDoList){
        return ToDoListInfra.builder()
                .id(toDoList.getId())
                .name(toDoList.getName())
                .description(toDoList.getDescription())
                .user(toDoList.getUser())
                .date(Calendar.getInstance().getTime())
                .build();
    }
}
