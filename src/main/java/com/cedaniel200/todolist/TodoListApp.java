package com.cedaniel200.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TodoListApp {
    public static void main(String[] args) {
        SpringApplication.run(TodoListApp.class, args);
    }
}
