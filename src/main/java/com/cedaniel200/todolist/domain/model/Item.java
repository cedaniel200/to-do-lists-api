package com.cedaniel200.todolist.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String descripcion;
    private boolean done;
}
