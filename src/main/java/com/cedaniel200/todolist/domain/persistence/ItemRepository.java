package com.cedaniel200.todolist.domain.persistence;

import com.cedaniel200.todolist.domain.model.Item;

public interface ItemRepository {
    Item create(long listId, Item item);

    Item getById(long itemId);

    boolean existsById(long itemId);
}
