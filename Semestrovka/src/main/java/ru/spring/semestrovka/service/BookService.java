package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Book;

import java.util.Optional;

public interface BookService {
    Optional<Book> readFile(String name, String link);
}
