package com.cedaniel200.todolist.infrastructure.config;

import com.cedaniel200.todolist.domain.items.ItemMediator;
import com.cedaniel200.todolist.domain.items.ItemMediatorDefault;
import com.cedaniel200.todolist.domain.persistence.ItemRepository;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.infrastructure.persistence.MongoDBItemRepository;
import com.cedaniel200.todolist.infrastructure.persistence.MongoListRepository;
import com.cedaniel200.todolist.infrastructure.persistence.SequenceGeneratorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemsConfiguration {

    @Bean
    public ItemRepository providesItemRepositoryInstance(SequenceGeneratorRepository sequenceGeneratorRepository,
                                                         MongoListRepository mongoListRepository) {
        return new MongoDBItemRepository(sequenceGeneratorRepository, mongoListRepository);
    }

    @Bean
    public ItemMediator providesItemMediatorInstance(ListRepository listRepository, ItemRepository repository) {
        return new ItemMediatorDefault(listRepository, repository);
    }
}
