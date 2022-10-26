package com.cedaniel200.todolist.infrastructure.config;

import com.cedaniel200.todolist.domain.lists.ListMediator;
import com.cedaniel200.todolist.domain.lists.ListMediatorDefault;
import com.cedaniel200.todolist.domain.lists.ListValidator;
import com.cedaniel200.todolist.domain.model.ToDoList;
import com.cedaniel200.todolist.domain.persistence.ListRepository;
import com.cedaniel200.todolist.domain.util.Validator;
import com.cedaniel200.todolist.infrastructure.persistence.MongoDBListRepository;
import com.cedaniel200.todolist.infrastructure.persistence.MongoListRepository;
import com.cedaniel200.todolist.infrastructure.persistence.SequenceGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class ListsConfiguration {

    @Bean
    public Validator<ToDoList> providesListValidatorInstance() {
        return new ListValidator();
    }

    @Bean
    public SequenceGeneratorRepository providesSequenceGeneratorRepositoryInstance(@Autowired MongoOperations mongoOperations) {
        return new SequenceGeneratorRepository(mongoOperations);
    }

    @Bean
    public ListRepository providesListRepositoryInstance(SequenceGeneratorRepository sequenceGeneratorRepository,
                                                         @Autowired MongoListRepository mongoListRepository) {
        return new MongoDBListRepository(sequenceGeneratorRepository, mongoListRepository);
    }

    @Bean
    public ListMediator providesListMediatorInstance(Validator<ToDoList> validator, ListRepository listRepository) {
        return new ListMediatorDefault(validator, listRepository);
    }

}
