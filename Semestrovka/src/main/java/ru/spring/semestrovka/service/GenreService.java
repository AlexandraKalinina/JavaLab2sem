package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    void readFile(String name, Book book);
    Optional<Genre> getGenre(Genre genre);
    boolean checkGenre(Genre genre, Book book);
}
