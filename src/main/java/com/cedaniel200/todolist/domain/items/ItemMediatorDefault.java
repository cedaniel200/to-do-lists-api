package com.cedaniel200.todolist.domain.items;

import com.cedaniel200.todolist.domain.exception.NotFoundException;
import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.domain.persistence.ItemRepository;

public class ItemMediatorDefault implements ItemMediator {
    private final ItemRepository itemRepository;

    public ItemMediatorDefault(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(long listId, Item item) {
        return itemRepository.create(listId, item);
    }

    @Override
    public Item getById(long itemId) {
        validateIfExists(itemId);
        return itemRepository.getById(itemId);
    }

    private void validateIfExists(long itemId) {
        if(!this.itemRepository.existsById(itemId))
            throw new NotFoundException(String.format("item with id %s not found", itemId));
    }
}
