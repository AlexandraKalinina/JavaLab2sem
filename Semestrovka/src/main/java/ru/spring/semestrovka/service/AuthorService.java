package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void readFile(String name, String surname, String patronymic, Book book);

    boolean checkAuthor(Author author, Book book);

    Optional<Author> getAuthor(Author author);

    List<Author> getAuthorBySurname(String surname);

    Optional<Author> find(Long id);

    List<Book> getBooks(Long id_author);
}
