package ru.itis.javalab.mongodb.querydsl.querydsl.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Book;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.QBook;


public interface BookRepository
        extends MongoRepository<Book, String>,
        QuerydslPredicateExecutor<Book>,
        QuerydslBinderCustomizer<QBook>{
    @Override
    default void customize(QuerydslBindings querydslBindings, QBook qBook) {
        querydslBindings.bind(qBook.links.any().link).as("link.link").first(
                StringExpression::containsIgnoreCase
        );
    }
}
