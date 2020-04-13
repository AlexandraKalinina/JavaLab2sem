package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> readFile(String name, String surname, String patronymic, Book book);
    Optional<Author> getAuthor(Author author);
}
