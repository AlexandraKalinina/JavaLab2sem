package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositories extends CrudRepositories<Author, Long>  {
    List<Author> getAuthorsByIdBook(Long id);

}
