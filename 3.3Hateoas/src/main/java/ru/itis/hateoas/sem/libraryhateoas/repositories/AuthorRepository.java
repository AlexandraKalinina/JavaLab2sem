package ru.itis.hateoas.sem.libraryhateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.sem.libraryhateoas.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @RestResource(path = "byNameAndSurname", rel = "NameSurname")
    List<Author> findAllByNameAndSurname(String name, String surname);
}
