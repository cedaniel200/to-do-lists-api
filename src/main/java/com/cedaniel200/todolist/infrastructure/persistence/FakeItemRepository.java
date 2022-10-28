package com.cedaniel200.todolist.infrastructure.persistence;

import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.domain.persistence.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeItemRepository implements ItemRepository {
    public final List<Item> items;

    public FakeItemRepository() {
        items = new ArrayList<>();
    }

    @Override
    public Item create(long listId, Item item) {
        items.add(item);
        item.setId(items.size() - 1);
        return item;
    }

    @Override
    public Item getById(long itemId) {
        --itemId;
        return items.get((int) itemId);
    }

    @Override
    public boolean existsById(long itemId) {
        --itemId;
        if(items.isEmpty() || items.size() <  itemId) return false;
        return items.get((int)itemId) != null;
    }
}