package com.cedaniel200.todolist.infrastructure.persistence;

import com.cedaniel200.todolist.domain.model.ToDoList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoListRepository extends MongoRepository<ToDoList, Long> {
}
