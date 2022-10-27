package com.cedaniel200.todolist.infrastructure.persistence;

import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;

import java.util.List;
import java.util.Optional;

import static com.cedaniel200.todolist.infrastructure.model.ToDoListInfra.SEQUENCE_NAME;

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
    public void delete(ToDoList toDoList) {
        mongoListRepository.delete(toDoList);
    }
}
