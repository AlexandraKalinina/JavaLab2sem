package ru.itis.mongodb.java.mongodbjava.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        GenreRepository genreRepository = context.getBean(GenreRepository.class);

        //save
        Author author = Author.builder()
                .name("Oscar")
                .surname("Wilde")
                .build();
        Author savedAuthor = authorRepository.save(author);

        Genre genre = Genre.builder()
                .name("Play")
                .build();

        Genre savedGenre = genreRepository.save(genre);

        Book book = Book.builder()
                .name("Salome")
                .size(2)
                .state("UnBlocked")
                .authors(savedAuthor)
                .genres(savedGenre)
                .build();
        bookRepository.save(book);

        //find
        Optional<Book> book1 = bookRepository.findById("5fdd11c7c180b44ff0c7df20");
        System.out.println(book1.get().toString());

        //update
        book1.get().setState("Blocked");
        bookRepository.save(book1.get());

        //delete
        bookRepository.delete(book1.get());
    }
}
