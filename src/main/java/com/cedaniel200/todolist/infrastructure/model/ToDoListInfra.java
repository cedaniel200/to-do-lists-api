package com.cedaniel200.todolist.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("lists")
public class ToDoListInfra {

    @Transient
    public static final String SEQUENCE_NAME = "lists_sequence";

    @Id
    private long id;
    private String name;
    private String description;
    private String user;
    private List<Item> items;
    private Date date;

}
