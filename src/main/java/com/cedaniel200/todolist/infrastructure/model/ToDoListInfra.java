package com.cedaniel200.todolist.infrastructure.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ToDoListInfra {

    private long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    @Email
    private String user;
    private Date date;

}
