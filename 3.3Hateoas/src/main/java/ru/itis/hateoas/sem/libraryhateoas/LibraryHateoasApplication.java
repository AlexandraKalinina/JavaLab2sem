package ru.itis.hateoas.sem.libraryhateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoas.sem.libraryhateoas.model.*;
import ru.itis.hateoas.sem.libraryhateoas.repositories.*;

import java.util.Collections;

import static java.util.Arrays.asList;


@SpringBootApplication
public class LibraryHateoasApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LibraryHateoasApplication.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        GenreRepository genreRepository = context.getBean(GenreRepository.class);
        MessageRepository messageRepository = context.getBean(MessageRepository.class);

        User sanya = User.builder()
                .name("Sanya")
                .email("alekskalin1na@yandex.ru")
                .password("123")
                .role(Role.USER)
                .build();
        User name = User.builder()
                .name("Lada")
                .email("ladalada@gmail.com")
                .password("123")
                .role(Role.USER)
                .build();
        userRepository.saveAll(asList(sanya, name));

        Author schildt = Author.builder()
                .name("Herbert")
                .surname("Schildt")
                .patronymic(" ")
                .build();
        Author turgenev = Author.builder()
                .name("Ivan")
                .surname("Turgenev")
                .patronymic("Sergeevich")
                .build();
        authorRepository.saveAll(asList(schildt, turgenev));

        Genre program = Genre.builder()
                .name("Program")
                .build();
        Genre story = Genre.builder()
                .name("Story")
                .build();
        genreRepository.saveAll(asList(program, story));

        Book java8 = Book.builder()
                .name("Java 8")
                .authors(Collections.singletonList(schildt))
                .genres(Collections.singletonList(program))
                .text("ссылка на текст книги")
                .state("unBlocking")
                .size(3L)
                .type("txt")
                .build();
        Book asya = Book.builder()
                .name("Asya")
                .authors(Collections.singletonList(turgenev))
                .genres(Collections.singletonList(story))
                .text("ссылка на текст книги")
                .size(3L)
                .state("Blocking")
                .type("txt")
                .build();
        bookRepository.saveAll(asList(java8, asya));

        Message message_sanya = Message.builder()
                .user(sanya)
                .text("This book is cool")
                .book(java8)
                .build();
        Message message_name = Message.builder()
                .user(name)
                .text("класс")
                .book(asya)
                .build();
        messageRepository.saveAll(asList(message_sanya, message_name));
    }

}
