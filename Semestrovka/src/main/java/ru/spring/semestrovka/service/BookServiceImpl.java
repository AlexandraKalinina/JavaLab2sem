package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.AuthorRepositories;
import ru.spring.semestrovka.repositories.BookRepositories;

import java.util.ArrayList;
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
    public Optional<Book> readFile(String link) {
        Optional<Book> book = bookRepositories.getBookByPath(link);
        if (book.isPresent()) {
            return book;
        } else throw new IllegalArgumentException("Book isn't found");
    }

    @Override
    public List<Book> getListBookByName(String name) {
        List<Book> books = bookRepositories.getBooksByName(name);
        if (books.size()!=0) {
            return books;
        } else return new ArrayList<>();
    }


}
