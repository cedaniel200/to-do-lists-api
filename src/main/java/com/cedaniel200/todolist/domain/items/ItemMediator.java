package com.cedaniel200.todolist.domain.items;

import com.cedaniel200.todolist.domain.model.Item;

import java.util.List;

public interface ItemMediator {
    Item create(long listId, Item item);

    Item getById(long listId, long itemId);

    List<Item> getAll(long listId);
}
