package com.cedaniel200.todolist.infrastructure.config;

import com.cedaniel200.todolist.domain.lists.ListMediator;
import com.cedaniel200.todolist.domain.lists.ListMediatorDefault;
import com.cedaniel200.todolist.domain.lists.ListValidator;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.domain.util.Validator;
import com.cedaniel200.todolist.infrastructure.persistence.MemoryListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListsConfiguration {

    @Bean
    public Validator<ToDoList> providesListValidatorInstance(){
        return new ListValidator();
    }


    @Bean
    public ListRepository providesListRepositoryInstance(){
        return new MemoryListRepository();
    }

    @Bean
    public ListMediator providesListMediatorInstance(Validator<ToDoList> validator, ListRepository listRepository){
        return new ListMediatorDefault(validator, listRepository);
    }

}
