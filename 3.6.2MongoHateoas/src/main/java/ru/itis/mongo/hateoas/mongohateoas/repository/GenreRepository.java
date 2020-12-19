package ru.itis.mongo.hateoas.mongohateoas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongo.hateoas.mongohateoas.model.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
