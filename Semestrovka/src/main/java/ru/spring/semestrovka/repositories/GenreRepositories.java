package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.util.List;

public interface GenreRepositories extends CrudRepositories<Genre, Long>  {
    List<Genre> getGenreByIdBook(Book book);
}
