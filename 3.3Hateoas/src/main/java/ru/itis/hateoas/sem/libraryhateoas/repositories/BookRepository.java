package ru.itis.hateoas.sem.libraryhateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.sem.libraryhateoas.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @RestResource(path = "byName", rel = "Name")
    List<Book> findAllByName(String name);
}
