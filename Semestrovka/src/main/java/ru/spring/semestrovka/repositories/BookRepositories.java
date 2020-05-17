package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.User;

import java.util.List;
import java.util.Optional;

public interface BookRepositories extends CrudRepositories<Book, Long> {
    Optional<Book> getBookByPath(String path);
    List<Book> getBooksByName(String name);
    Long getSumSizeBookOwner(User user);
}
