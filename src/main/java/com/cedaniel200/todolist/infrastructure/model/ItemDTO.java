package com.cedaniel200.todolist.infrastructure.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("items")
public class ItemDTO {
    private long id;
    private String description;
    private boolean done;
}
