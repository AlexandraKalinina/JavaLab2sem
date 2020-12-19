package ru.itis.mongo.hateoas.mongohateoas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongo.hateoas.mongohateoas.model.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
