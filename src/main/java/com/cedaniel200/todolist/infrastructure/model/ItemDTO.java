package com.cedaniel200.todolist.infrastructure.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Transient;

@Data
@Builder
public class ItemDTO {
    @Transient
    public static final String SEQUENCE_NAME = "items_sequence";

    private long id;
    private String description;
    private boolean done;
}
