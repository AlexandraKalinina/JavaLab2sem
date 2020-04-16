package ru.spring.semestrovka.service;

import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> readFile(String name, Book book);
    List<Genre> getGenre(Genre genre);
}
