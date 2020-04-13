package ru.spring.semestrovka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String text;

    @OneToMany(mappedBy = "book")
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    private List<Genre> genres;

    public Book(String name, String text) {
        this.name = name;
        this.text = text;
    }

}
