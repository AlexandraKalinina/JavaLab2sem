package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> find(Long id);
    List<Book> getListBookByName(String name);
    List<Book> getBooksByAuthor(Long id);
}
