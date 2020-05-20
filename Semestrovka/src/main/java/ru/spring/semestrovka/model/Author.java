package ru.spring.semestrovka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String patronymic;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name="author_book",
            joinColumns = @JoinColumn(name="author_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName="id")
    )
    private List<Book> books;

    public Author(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return  Objects.equals(name, author.name) &&
                Objects.equals(surname, author.surname) &&
                Objects.equals(patronymic, author.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic);
    }
}
