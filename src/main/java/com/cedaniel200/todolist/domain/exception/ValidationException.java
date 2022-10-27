package com.cedaniel200.todolist.domain.exception;

import com.cedaniel200.todolist.domain.model.Error;

import java.util.List;

public class ValidationException extends IllegalArgumentException {
    public static final String DEFAULT_MESSAGE = "Ocurrio un error al realizar una validación";
    private final String object;
    private final List<Error> errors;

    public ValidationException(String object, List<Error> errors) {
        super(errors.stream().map(Error::getDetail).reduce((s, s2) -> s += " - " + s2).orElse(DEFAULT_MESSAGE));
        this.object = object;
        this.errors = errors;
    }

    public String getObject() {
        return object;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
