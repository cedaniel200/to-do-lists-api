package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.model.Error;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cedaniel200.todolist.domain.util.StringUtils.*;

public class ListValidator implements Validator<ToDoList> {

    @Override
    public Optional<List<Error>> validate(ToDoList toDoList) {
        List<Error> errors = new ArrayList<>();
        if(isNullOrBlank(toDoList.getName())) errors.add(createError("Name", "Name is empty"));
        if(isNullOrBlank(toDoList.getUser())) errors.add(createError("User","User is empty"));
        if(isNotNullOrBlank(toDoList.getUser()) && isNotEmail(toDoList.getUser()))
            errors.add(createError("User", "The user does not have the email format"));

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    private Error createError(String title, String detail) {
        return Error.builder()
                .title(title)
                .detail(detail)
                .build();
    }
}
