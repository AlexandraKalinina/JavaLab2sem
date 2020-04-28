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
@Table(name = "book")
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

    @OneToMany(mappedBy = "book")
    private List<Message> messages;

    public Book(Long id, String name, String text, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.authors = authors;
        this.genres = genres;
    }

    public Book(String name) {
        this.name = name;
    }
}
