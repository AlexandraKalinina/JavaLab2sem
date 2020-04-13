package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> readFile(String name, Book book);
    Optional<Genre> getGenre(Genre genre);
}
