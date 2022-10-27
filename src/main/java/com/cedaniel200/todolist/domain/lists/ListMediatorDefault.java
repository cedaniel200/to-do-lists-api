package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.exception.NotFoundException;
import com.cedaniel200.todolist.domain.exception.ValidationException;
import com.cedaniel200.todolist.domain.model.Error;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.domain.util.Validator;

import java.util.List;
import java.util.Optional;

import static com.cedaniel200.todolist.domain.util.StringUtils.isNullOrBlank;

public class ListMediatorDefault implements ListMediator {

    private final Validator<ToDoList> validator;
    private final ListRepository listRepository;

    public ListMediatorDefault(Validator<ToDoList> validator, ListRepository listRepository) {
        this.validator = validator;
        this.listRepository = listRepository;
    }

    public ToDoList create(ToDoList toDoList) {
        validate(toDoList);
        if(toDoList.getDescription() == null) toDoList.setDescription("");
        return listRepository.save(toDoList);
    }

    @Override
    public ToDoList getListById(long listId) {
        Optional<ToDoList> toDoList = this.listRepository.findById(listId);
        return toDoList.orElseThrow(() -> new NotFoundException(String.format("list with id %s not found", listId)));
    }

    @Override
    public List<ToDoList> getAllLists() {
        return this.listRepository.findAll();
    }

    @Override
    public void delete(long listId) {
        ToDoList toDoListToDelete = getListById(listId);
        this.listRepository.delete(toDoListToDelete);
    }

    @Override
    public void update(ToDoList toDoList) {
        ToDoList toDoListToSaved = getListById(toDoList.getId());
        String name = isNullOrBlank(toDoList.getName()) ?
                toDoListToSaved.getName() :
                toDoList.getName();
        String description = prepareDescriptionToUpdate(toDoListToSaved.getDescription(), toDoList.getDescription());

        ToDoList toDoListToUpdate = ToDoList.builder()
                .id(toDoListToSaved.getId())
                .items(toDoListToSaved.getItems())
                .user(toDoListToSaved.getUser())
                .description(description)
                .name(name)
                .build();
        this.listRepository.update(toDoListToUpdate);
    }

    private String prepareDescriptionToUpdate(String descriptionSaved, String descriptionToUpdate) {
        if(descriptionToUpdate != null && descriptionToUpdate.isBlank()) return null;
        return descriptionToUpdate == null ? descriptionSaved : descriptionToUpdate;
    }

    private void validate(ToDoList toDoList) {
        Optional<List<Error>> errors = validator.validate(toDoList);
        if(errors.isPresent())
            throw new ValidationException("ToDoList", errors.get());
    }

}
