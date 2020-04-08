package ru.spring.semestrovka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private Long id;

    private String name;
    private String text;

    public Book(String name, String text) {
        this.name = name;
        this.text = text;
    }
}
