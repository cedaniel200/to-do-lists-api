package com.cedaniel200.todolist.domain.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Error implements Serializable {
    private static final long serialVersionUID = 2405172041950251823L;
    private String title;
    private String detail;
}
