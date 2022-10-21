package com.cedaniel200.todolist.infrastructure.config;

import com.cedaniel200.todolist.domain.lists.ListCreator;
import com.cedaniel200.todolist.domain.lists.ListCreatorDefault;
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
    public ListCreator providesListCreatorInstance(ListRepository listRepository){
        return new ListCreatorDefault(listRepository);
    }

}
