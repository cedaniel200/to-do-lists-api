package com.cedaniel200.todolist.infrastructure.model;

import java.util.List;

public class ListCollection {
    private final int size;
    private final List<ToDoListInfra> lists;

    public ListCollection(List<ToDoListInfra> lists) {
        this.size = lists.size();
        this.lists = lists;
    }

    public int getSize() {
        return size;
    }

    public List<ToDoListInfra> getLists() {
        return lists;
    }
}
