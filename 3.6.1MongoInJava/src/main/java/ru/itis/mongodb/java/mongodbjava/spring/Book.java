package ru.itis.mongodb.java.mongodbjava.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String _id;
    private String name;
    private Integer size;
    private Author author;
    private Genre genres;
    private String state;
    private String path;

    public Book(String name, Integer size, String state) {
        this.name = name;
        this.size = size;
        this.state = state;
    }
}
