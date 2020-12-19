package ru.itis.mongo.hateoas.mongohateoas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "books")
public class Book {
    @Id
    private String _id;
    private String name;
    private Integer size;

    @DBRef
    private Author authors;
    @DBRef
    private Genre genres;

    private String state;
    private String path;
}
