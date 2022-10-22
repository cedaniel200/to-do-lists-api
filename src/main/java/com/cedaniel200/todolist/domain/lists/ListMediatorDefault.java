package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.exception.ValidationException;
import com.cedaniel200.todolist.domain.model.Error;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.domain.util.Validator;

import java.util.List;
import java.util.Optional;

public class ListMediatorDefault implements ListMediator {

    private final Validator<ToDoList> validator;
    private final ListRepository listRepository;

    public ListMediatorDefault(Validator<ToDoList> validator, ListRepository listRepository) {
        this.validator = validator;
        this.listRepository = listRepository;
    }

    public ToDoList create(ToDoList toDoList) {
        validate(toDoList);
        return listRepository.save(toDoList);
    }

    private void validate(ToDoList toDoList) {
        Optional<List<Error>> errors = validator.validate(toDoList);
        if(errors.isPresent())
            throw new ValidationException("ToDoList", errors.get());
    }

}
