package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.AuthorRepositories;
import ru.spring.semestrovka.repositories.BookRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    @Qualifier("bookRepositories")
    private BookRepositories bookRepositories;

    @Autowired
    @Qualifier("authorRepositories")
    private AuthorRepositories authorRepositories;

    @Override
    public Optional<Book> readFile(String name, String link) {
        Book current_book = Book.builder()
                .name(name)
                .text(link)
                .build();
        Optional<Book> book = bookRepositories.getBookByPath(link);
        if (!book.isPresent()) {
            bookRepositories.save(current_book);
            return bookRepositories.getBookByPath(link);
        } else {
            List<Author> authors = authorRepositories.getAuthorsByIdBook(book.get());
            if (authors.size() == 0) {
                bookRepositories.save(current_book);
                return bookRepositories.getBookByPath(link);
            }
            return book;
        }
    }



}
