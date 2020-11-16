package ru.itis.hateoas.sem.libraryhateoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.sem.libraryhateoas.model.Book;
import ru.itis.hateoas.sem.libraryhateoas.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book download(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.download();
        bookRepository.save(book);
        return book;
    }
}
