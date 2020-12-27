package ru.itis.javalab.mongodb.querydsl.querydsl.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Author;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.QAuthor;


public interface AuthorRepository
        extends MongoRepository<Author, String>,
        QuerydslPredicateExecutor<Author>,
        QuerydslBinderCustomizer<QAuthor> {
    @Override
    default void customize(QuerydslBindings querydslBindings, QAuthor qAuthor) {

    }
}
