package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositories extends CrudRepositories<Author, Long>  {
    Optional<Author> getAuthorBySNP(String name, String surname, String patronymic);
    List<Author> getAuthorsBySurname(String surname);
    void update(Author author);
}
