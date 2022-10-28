package com.cedaniel200.todolist.domain.lists;

import com.cedaniel200.todolist.domain.exception.NotFoundException;
import com.cedaniel200.todolist.domain.items.ItemMediator;
import com.cedaniel200.todolist.domain.items.ItemMediatorDefault;
import com.cedaniel200.todolist.domain.model.Item;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.util.FakeItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ItemMediatorTest {

    private Item itemRice;
    private Item itemMilk;
    @Mock
    private ListRepository listRepository;
    private ItemMediator mediator;

    @BeforeEach
    void setupt() {
        itemRice = Item.builder().id(0).description("Comprar Arroz").done(false).build();
        itemMilk = Item.builder().id(1).description("Comprar Leche").done(false).build();
        openMocks(this);
        when(listRepository.existsById(any(Long.class))).thenReturn(true);
        this.mediator = new ItemMediatorDefault(listRepository, new FakeItemRepository());
    }

    @Test
    void shouldCreateAnItem() {
        Item itemSaved = mediator.create(1L, itemRice);

        assertEquals(0, itemSaved.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenListIdDoesntExists() {
        when(listRepository.existsById(any(Long.class))).thenReturn(false);
        long id = 5L;

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> mediator.create(id, itemRice),
                "Debia lanzar un NotFoundException cuando la lista no existe, pero no ocurrio");

        assertTrue(exception.getMessage().contains(String.format("list with id %s not found", id)));
    }

    @Test
    void shouldGetAnItemById() {
        mediator.create(1L, itemRice);

        Item itemSaved = mediator.getById(0L, itemRice.getId());

        assertEquals(0, itemSaved.getId());
    }

    @Test
    void shouldGetItemRiceAndItemMilkById() {
        mediator.create(1L, itemRice);
        mediator.create(1L, itemMilk);

        Item itemXSaved = mediator.getById(0L, itemRice.getId());
        Item itemYSaved = mediator.getById(0L, itemMilk.getId());

        assertEquals(0, itemXSaved.getId());
        assertEquals(1, itemYSaved.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenItemIdDoesntExists() {
        long id = 1L;

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> mediator.getById(0L, id),
                "Debia lanzar un NotFoundException cuando un item no existe, pero no ocurrio");

        assertTrue(exception.getMessage().contains(String.format("item with id %s not found", id)));
    }
}
