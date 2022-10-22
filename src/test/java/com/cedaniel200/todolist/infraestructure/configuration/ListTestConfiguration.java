package com.cedaniel200.todolist.infraestructure.configuration;

import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.infrastructure.persistence.MemoryListRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ListTestConfiguration {

    @Bean
    public ListRepository providesListRepositoryInstance(){
        return new MemoryListRepository();
    }
}