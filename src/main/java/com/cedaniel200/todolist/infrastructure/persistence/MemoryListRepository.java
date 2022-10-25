package com.cedaniel200.todolist.infrastructure.persistence;

import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryListRepository implements ListRepository {
    private final List<ToDoList> lists;

    public MemoryListRepository() {
        lists = new ArrayList();
    }

    @Override
    public ToDoList save(ToDoList toDoList) {
        lists.add(toDoList);
        ToDoList toDoListSaved = new ToDoList();
        toDoListSaved.setId(lists.size() - 1);
        toDoListSaved.setName(toDoList.getName());
        toDoListSaved.setDescription(toDoList.getDescription());
        toDoListSaved.setUser(toDoList.getUser());
        return toDoListSaved;
    }

    @Override
    public Optional<ToDoList> findById(long listId) {
        if(lists.isEmpty() || lists.size() <  listId) return Optional.empty();
        return Optional.ofNullable(lists.get((int) listId));
    }
}
