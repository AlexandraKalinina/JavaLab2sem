package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> readFile(String name, String surname, String patronymic, Book book);
    List<Author> getAuthor(Author author);
}
