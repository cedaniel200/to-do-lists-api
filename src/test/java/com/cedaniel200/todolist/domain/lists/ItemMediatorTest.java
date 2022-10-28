package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.exception.NotFoundException;
import com.cedaniel200.todolist.domain.items.ItemMediator;
import com.cedaniel200.todolist.domain.items.ItemMediatorDefault;
import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.infrastructure.persistence.FakeItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemMediatorTest {

    private Item itemRice;
    private Item itemMilk;
    private ItemMediator mediator;

    @BeforeEach
    void setupt() {
        itemRice = Item.builder().id(0).description("Comprar Arroz").done(false).build();
        itemMilk = Item.builder().id(1).description("Comprar Leche").done(false).build();
        this.mediator = new ItemMediatorDefault(new FakeItemRepository());
    }

    @Test
    void shouldCreateAnItem() {
        Item itemSaved = mediator.create(1L, itemRice);

        assertEquals(0, itemSaved.getId());
    }

    @Test
    void shouldGetAnItemById() {
        mediator.create(1L, itemRice);

        Item itemSaved = mediator.getById(itemRice.getId());

        assertEquals(0, itemSaved.getId());
    }

    @Test
    void shouldGetItemRiceAndItemMilkById() {
        mediator.create(1L, itemRice);
        mediator.create(1L, itemMilk);

        Item itemXSaved = mediator.getById(itemRice.getId());
        Item itemYSaved = mediator.getById(itemMilk.getId());

        assertEquals(0, itemXSaved.getId());
        assertEquals(1, itemYSaved.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenWhenItemIdDoesntExists(){
        long id = 1L;

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> mediator.getById(id),
                "Debia lanzar un NotFoundException cuando un item no existe, pero no ocurrio");

        assertTrue(exception.getMessage().contains(String.format("item with id %s not found", id)));
    }
}
