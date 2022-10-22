package com.cedaniel200.todolist.domain.util;

import com.cedaniel200.todolist.domain.model.Error;

import java.util.List;
import java.util.Optional;

public interface Validator<T> {

    Optional<List<Error>> validate(T value);
}
