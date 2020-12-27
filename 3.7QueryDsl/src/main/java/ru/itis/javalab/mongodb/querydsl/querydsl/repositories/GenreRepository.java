package ru.itis.javalab.mongodb.querydsl.querydsl.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Genre;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.QAuthor;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.QGenre;

public interface GenreRepository
        extends MongoRepository<Genre, String>,
        QuerydslPredicateExecutor<Genre>,
        QuerydslBinderCustomizer<QGenre> {
    @Override
    default void customize(QuerydslBindings querydslBindings, QGenre qGenre) {

    }
}
