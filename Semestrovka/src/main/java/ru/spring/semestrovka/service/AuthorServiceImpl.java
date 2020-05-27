package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.AuthorRepositories;
import ru.spring.semestrovka.repositories.BookRepositories;

import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    @Qualifier("authorRepositories")
    private AuthorRepositories authorRepositories;

    @Autowired
    private BookRepositories bookRepositories;

    @Override
    @Transactional
    public void readFile(String name, String surname, String patronymic, Book book) {
        Author current_author = Author.builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .books(new HashSet<>())
                .build();
        Optional<Author> author = getAuthor(current_author);
        if (!author.isPresent()) {
            current_author.addBook(book);
            authorRepositories.save(current_author);
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
    @Transactional
    public Set<Book> getBooks(Long id_author) {
        Optional<Author> author = find(id_author);
        return author.get().getBooks();
    }
}
