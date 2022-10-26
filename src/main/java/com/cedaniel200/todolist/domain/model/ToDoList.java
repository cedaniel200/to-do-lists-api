package com.cedaniel200.todolist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoList {

    private long id;
    private String name;
    private String description;
    private String user;
    private List<Item> items;

}
