package com.cedaniel200.todolist.domain.items;

import com.cedaniel200.todolist.domain.model.Item;

public interface ItemMediator {
    Item create(long listId, Item item);

    Item getById(long itemId);
}
