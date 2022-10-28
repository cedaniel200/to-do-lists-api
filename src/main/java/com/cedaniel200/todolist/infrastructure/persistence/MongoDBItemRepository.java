package com.cedaniel200.todolist.infrastructure.persistence;

import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cedaniel200.todolist.infrastructure.model.ItemDTO.SEQUENCE_NAME;

public class MongoDBItemRepository implements ItemRepository {

    private final SequenceGeneratorRepository sequenceGeneratorRepository;
    private final MongoListRepository mongoListRepository;

    public MongoDBItemRepository(SequenceGeneratorRepository sequenceGeneratorRepository, MongoListRepository mongoListRepository) {
        this.sequenceGeneratorRepository = sequenceGeneratorRepository;
        this.mongoListRepository = mongoListRepository;
    }

    @Override
    public Item create(long listId, Item item) {
        ToDoList toDoList = mongoListRepository.findById(listId).get();
        List<Item> items = toDoList.getItems();
        if(items == null) items = new ArrayList<>();
        item.setId(sequenceGeneratorRepository.generateSequence(SEQUENCE_NAME));
        items.add(item);
        toDoList.setItems(items);
        mongoListRepository.save(toDoList);
        return item;
    }

    @Override
    public Optional<Item> getById(long listId, long itemId) {
        Optional<ToDoList> toDoList = mongoListRepository.findById(listId);
        return toDoList.flatMap(toDoListSaved -> toDoListSaved.getItems()
                .stream()
                .filter(item -> item.getId() == itemId)
                .findFirst());
    }

    @Override
    public List<Item> getAll(long listId) {
        Optional<ToDoList> toDoList = mongoListRepository.findById(listId);
        return toDoList.map(ToDoList::getItems).orElse(new ArrayList<>());
    }

    @Override
    public boolean existsById(long listId, long itemId) {
        return getById(listId, itemId).isPresent();
    }
}
