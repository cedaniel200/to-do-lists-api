package com.cedaniel200.todolist.infrastructure.model;

import java.util.List;

public class ListCollection {
    private final int size;
    private final List<ToDoListDTO> lists;

    public ListCollection(List<ToDoListDTO> lists) {
        this.size = lists.size();
        this.lists = lists;
    }

    public int getSize() {
        return size;
    }

    public List<ToDoListDTO> getLists() {
        return lists;
    }
}
