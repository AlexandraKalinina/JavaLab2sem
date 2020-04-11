package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.model.Book;

import java.util.Optional;

public interface BookRepositories extends CrudRepositories<Book, Long> {
    Optional<Book> getBookByPath(String path);
}
