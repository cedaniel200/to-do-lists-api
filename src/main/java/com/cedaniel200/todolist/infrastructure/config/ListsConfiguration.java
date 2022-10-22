package com.cedaniel200.todolist.infrastructure.config;

import com.cedaniel200.todolist.domain.lists.ListMediator;
import com.cedaniel200.todolist.domain.lists.ListMediatorDefault;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.infrastructure.persistence.MemoryListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListsConfiguration {

    @Bean
    public ListRepository providesListRepositoryInstance(){
        return new MemoryListRepository();
    }

    @Bean
    public ListMediator providesListMediatorInstance(ListRepository listRepository){
        return new ListMediatorDefault(listRepository);
    }

}
