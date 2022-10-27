package com.cedaniel200.todolist.infrastructure.mappers;

import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.infrastructure.model.ToDoListDTO;

import java.util.Calendar;

public class ToDoListMapper {

    private ToDoListMapper() {
    }

    public static ToDoList toToDoList(ToDoListDTO toDoListDTO) {
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListDTO.getId());
        toDoList.setName(toDoListDTO.getName());
        toDoList.setDescription(toDoListDTO.getDescription());
        toDoList.setUser(toDoListDTO.getUser());
        return toDoList;
    }

    public static ToDoListDTO toToDoListDTO(ToDoList toDoList) {
        return ToDoListDTO.builder()
                .id(toDoList.getId())
                .name(toDoList.getName())
                .description(toDoList.getDescription())
                .user(toDoList.getUser())
                .date(Calendar.getInstance().getTime())
                .build();
    }
}
