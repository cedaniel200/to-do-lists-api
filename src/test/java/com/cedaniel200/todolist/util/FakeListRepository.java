package com.cedaniel200.todolist.util;

import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeListRepository implements ListRepository {
    private final List<ToDoList> lists;

    public FakeListRepository() {
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

    @Override
    public List<ToDoList> findAll() {
        return lists;
    }

    @Override
    public void delete(long listId) {
        lists.remove(listId);
    }

    @Override
    public void update(ToDoList toDoList) {
        lists.removeIf(list -> list.getId() == toDoList.getId());
        lists.add(toDoList);
    }

    @Override
    public boolean existsById(long listId) {
        return lists.get((int)listId) != null;
    }
}
