package com.cedaniel200.todolist.infrastructure.persistence;

import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;

import java.util.List;
import java.util.Optional;

import static com.cedaniel200.todolist.domain.util.StringUtils.isNullOrBlank;
import static com.cedaniel200.todolist.infrastructure.model.ToDoListDTO.SEQUENCE_NAME;

public class MongoDBListRepository implements ListRepository {

    private final SequenceGeneratorRepository sequenceGeneratorRepository;
    private final MongoListRepository mongoListRepository;

    public MongoDBListRepository(SequenceGeneratorRepository sequenceGeneratorRepository, MongoListRepository mongoListRepository) {
        this.sequenceGeneratorRepository = sequenceGeneratorRepository;
        this.mongoListRepository = mongoListRepository;
    }

    @Override
    public ToDoList save(ToDoList toDoList) {
        toDoList.setId(sequenceGeneratorRepository.generateSequence(SEQUENCE_NAME));
        return mongoListRepository.save(toDoList);
    }

    @Override
    public Optional<ToDoList> findById(long listId) {
        return mongoListRepository.findById(listId);
    }

    @Override
    public List<ToDoList> findAll() {
        return mongoListRepository.findAll();
    }

    @Override
    public void delete(long listId) {
        mongoListRepository.deleteById(listId);
    }

    @Override
    public void update(ToDoList toDoList) {
        ToDoList toDoListToSaved = findById(toDoList.getId()).get();
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
        mongoListRepository.save(toDoListToUpdate);
    }

    private String prepareDescriptionToUpdate(String descriptionSaved, String descriptionToUpdate) {
        if(descriptionToUpdate != null && descriptionToUpdate.isBlank()) return null;
        return descriptionToUpdate == null ? descriptionSaved : descriptionToUpdate;
    }

    @Override
    public boolean existsById(long listId) {
        return mongoListRepository.existsById(listId);
    }
}
