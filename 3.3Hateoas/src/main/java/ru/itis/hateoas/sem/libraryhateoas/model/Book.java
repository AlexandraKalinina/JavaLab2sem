package ru.itis.hateoas.sem.libraryhateoas.model;


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
    @GeneratedValue
    private Long id;

    private String name;
    private String text;

    private String type;
    private Long size;

    private String state;

    @ManyToMany
    @JoinTable( name="book_author",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName="id")
    )
    private List<Author> authors;

    @ManyToMany
    @JoinTable( name="book_genre",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="genre_id", referencedColumnName="id")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "book")
    private List<Message> messages;

    public void download() {
        if (this.state.equals("Blocking")) {
            this.state = "delete";
        } else if (this.state.equals("unBlocking")) {
            this.state = "unBlocking";
        }
    }
}
