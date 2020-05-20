package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositories extends CrudRepositories<Genre, Long>  {
    Optional<Genre> getGenreByName(String name);
    void update(Genre genre);
}
