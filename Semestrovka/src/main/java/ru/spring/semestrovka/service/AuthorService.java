package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> readFile(String name, String surname, String patronymic, Long id_book);
    Optional<Author> getAuthor(Author author);
}
