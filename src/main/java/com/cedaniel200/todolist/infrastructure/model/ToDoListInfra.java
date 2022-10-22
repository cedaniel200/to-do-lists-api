package com.cedaniel200.todolist.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDoListInfra {

    private long id;
    private String name;
    private String description;
    private String user;
    private Date date;

}
