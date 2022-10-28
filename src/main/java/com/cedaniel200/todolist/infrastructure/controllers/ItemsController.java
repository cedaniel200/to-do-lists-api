package com.cedaniel200.todolist.infrastructure.controllers;

import com.cedaniel200.todolist.domain.items.ItemMediator;
import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.infrastructure.mappers.ItemMapper;
import com.cedaniel200.todolist.infrastructure.model.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsController {

    private final ItemMediator itemMediatorr;

    public ItemsController(ItemMediator itemMediatorr) {
        this.itemMediatorr = itemMediatorr;
    }

    @PostMapping(path = "/lists/{listId}/items")
    public ResponseEntity<ItemDTO> create(@PathVariable("listId") long listId, @RequestBody ItemDTO itemDTO) {
        Item itemToCreate = ItemMapper.toItem(itemDTO);
        Item itemCreated = itemMediatorr.create(listId, itemToCreate);
        ItemDTO itemDTOCreated = ItemMapper.toItemDTO(itemCreated);
        return new ResponseEntity<>(itemDTOCreated, HttpStatus.CREATED);
    }

}
