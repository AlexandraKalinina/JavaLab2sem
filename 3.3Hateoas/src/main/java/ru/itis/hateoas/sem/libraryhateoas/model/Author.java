package ru.itis.hateoas.sem.libraryhateoas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String patronymic;
    private String copyright;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

}
