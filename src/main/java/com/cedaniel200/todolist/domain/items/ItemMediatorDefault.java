package com.cedaniel200.todolist.domain.items;

import com.cedaniel200.todolist.domain.exception.NotFoundException;
import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.domain.persistence.ItemRepository;
import com.cedaniel200.todolist.domain.persistence.ListRepository;

import java.util.Optional;

public class ItemMediatorDefault implements ItemMediator {
    private final ListRepository listRepository;
    private final ItemRepository itemRepository;

    public ItemMediatorDefault(ListRepository listRepository, ItemRepository itemRepository) {
        this.listRepository = listRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(long listId, Item item) {
        if(!this.listRepository.existsById(listId))
            throw new NotFoundException(String.format("list with id %s not found", listId));
        return itemRepository.create(listId, item);
    }

    @Override
    public Item getById(long listId, long itemId) {
        Optional<Item> item = itemRepository.getById(listId, itemId);
        return item.orElseThrow(() -> new NotFoundException(String.format("item with id %s not found", itemId)));
    }

    private void validateIfExists(long listId, long itemId) {
        if(!this.itemRepository.existsById(listId, itemId))
            throw new NotFoundException(String.format("item with id %s not found", itemId));
    }
}
