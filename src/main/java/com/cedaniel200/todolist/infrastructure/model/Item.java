package com.cedaniel200.todolist.infrastructure.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
public class Item {
    private String descripcion;
    private boolean done;
}
