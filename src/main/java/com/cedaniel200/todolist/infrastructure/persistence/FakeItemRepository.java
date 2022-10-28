package com.cedaniel200.todolist.infrastructure.persistence;

import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.domain.persistence.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeItemRepository implements ItemRepository {
    public final List<Item> items;

    public FakeItemRepository() {
        items = new ArrayList<>();
    }

    @Override
    public Item create(long listId, Item item) {
        items.add(item);
        item.setId((long)items.size() - 1);
        return item;
    }

    @Override
    public Optional<Item> getById(long listId, long itemId) {
        if(items.isEmpty() || items.size() <  itemId) return Optional.empty();
        return Optional.of(items.get((int) itemId));
    }

    @Override
    public List<Item> getAll(long listId) {
        return items;
    }

    @Override
    public boolean existsById(long listId, long itemId) {
        if(items.isEmpty() || items.size() <  itemId) return false;
        return items.get((int)itemId) != null;
    }
}