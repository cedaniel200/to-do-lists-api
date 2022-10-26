package com.cedaniel200.todolist.infrastructure.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "database_sequences")
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;
}
