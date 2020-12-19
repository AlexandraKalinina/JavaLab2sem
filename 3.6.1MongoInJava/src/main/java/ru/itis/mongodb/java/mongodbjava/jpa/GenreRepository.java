package ru.itis.mongodb.java.mongodbjava.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
