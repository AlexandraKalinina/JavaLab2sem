package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.AuthorRepositories;
import ru.spring.semestrovka.repositories.BookRepositories;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepositories bookRepositories;

    @Autowired
    private AuthorRepositories authorRepositories;

    @Override
    public Optional<Book> readFile(String name, String link) {
        Book current_book = Book.builder()
                .name(name)
                .text(link)
                .build();
        Optional<Book> book = bookRepositories.getUserByName(name, link);
        if (!book.isPresent()) {
            bookRepositories.save(current_book);
            return bookRepositories.getUserByName(name, link);
        } else {
            List<Author> authors = authorRepositories.getAuthorsByIdBook(book.get().getId());
            if (authors.size() == 0) {
                bookRepositories.save(current_book);
                return bookRepositories.getUserByName(name, link);
            }
            return book;
        }
    }


}
