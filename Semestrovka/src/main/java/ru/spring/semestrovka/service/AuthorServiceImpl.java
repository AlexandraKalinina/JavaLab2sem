package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.AuthorRepositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    @Qualifier("authorRepositories")
    private AuthorRepositories authorRepositories;

    @Override
    @Transactional
    public void readFile(String name, String surname, String patronymic, Book book) {
        Author current_author = Author.builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .build();
        Optional<Author> author = getAuthor(current_author);
        if (author.isPresent()) {
            if (!checkAuthor(author.get(),book)) {
                author.get().setBooks(Collections.singletonList(book));
                authorRepositories.update(author.get());
            }
        } else {
            current_author.setBooks(Collections.singletonList(book));
            authorRepositories.update(current_author);
        }
    }


   @Override
    public boolean checkAuthor(Author author, Book book) {
        return author.getBooks().contains(book);
    }

    @Override
    public Optional<Author> getAuthor(Author author) {
        return authorRepositories.getAuthorBySNP(author.getName(),
                author.getSurname(), author.getPatronymic());
    }

    @Override
    public List<Author> getAuthorBySurname(String surname) {
        List<Author> authors = authorRepositories.getAuthorsBySurname(surname);
        if (authors.size()!=0) {
            return authors;
        } else return new ArrayList<>();
    }

    @Override
    public Optional<Author> find(Long id) {
        Optional<Author> author = authorRepositories.find(id);
        if (author.isPresent()) {
            return author;
        } else throw new IllegalArgumentException("Author isn't found");
    }

    @Override
    public List<Book> getBooks(Long id_author) {
        Optional<Author> author = find(id_author);
        return author.get().getBooks();
    }
}
