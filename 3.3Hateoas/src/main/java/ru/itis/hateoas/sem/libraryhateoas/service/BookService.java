package ru.itis.hateoas.sem.libraryhateoas.service;

import ru.itis.hateoas.sem.libraryhateoas.model.Book;

import java.util.List;

public interface BookService {
    Book download(Long id);
}
