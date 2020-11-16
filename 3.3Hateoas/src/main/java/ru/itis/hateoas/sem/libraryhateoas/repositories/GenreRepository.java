package ru.itis.hateoas.sem.libraryhateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoas.sem.libraryhateoas.model.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @RestResource(path = "byName", rel = "Name")
    List<Genre> findAllByName(String name);
}
