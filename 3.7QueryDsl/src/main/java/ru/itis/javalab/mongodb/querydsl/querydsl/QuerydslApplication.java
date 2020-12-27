package ru.itis.javalab.mongodb.querydsl.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Author;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Book;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Genre;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Link;
import ru.itis.javalab.mongodb.querydsl.querydsl.repositories.AuthorRepository;
import ru.itis.javalab.mongodb.querydsl.querydsl.repositories.BookRepository;
import ru.itis.javalab.mongodb.querydsl.querydsl.repositories.GenreRepository;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "ru.itis.javalab.mongodb.querydsl.querydsl")
public class QuerydslApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(QuerydslApplication.class, args);
        BookRepository bookRepository =  context.getBean(BookRepository.class);
        AuthorRepository authorRepository =  context.getBean(AuthorRepository.class);
        GenreRepository genreRepository =  context.getBean(GenreRepository.class);

        Author author = Author.builder()
                .name("Author")
                .surname("AuthorSurname")
                .patronymic("AuthorAuthor")
                .build();
        Author savedAuthor = authorRepository.save(author);

        Genre genre = Genre.builder()
                .name("Genre")
                .build();

        Genre savedGenre = genreRepository.save(genre);

        Book book = Book.builder()
                .name("Book1")
                .path("c:/books/txt")
                .state("UnBlocking")
                .size(3)
                .authors(savedAuthor)
                .genres(savedGenre)
                .links(Collections.singletonList(
                        Link.builder()
                                .link("http://adviceBooks.ru")
                                .build()
                ))
                .build();
        Book savedBook = bookRepository.save(book);

    }

}
