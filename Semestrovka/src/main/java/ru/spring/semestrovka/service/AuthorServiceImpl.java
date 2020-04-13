package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.AuthorRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    @Qualifier("authorRepositories")
    private AuthorRepositories authorRepositories;

    @Override
    public Optional<Author> readFile(String name, String surname, String patronymic, Book book) {
        Author current_author = Author.builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .book(book)
                .build();
        Optional<Author> author = getAuthor(current_author);
        if (!author.isPresent()) {
            authorRepositories.save(current_author);
            return getAuthor(current_author);
        } else return author;
    }


    @Override
    public Optional<Author> getAuthor(Author author) {
        List<Author> authors = authorRepositories.getAuthorsByIdBook(author.getBook());
        if (authors.size() != 0) {
            for (Author a: authors) {
                if (a.getName().equals(author.getName()) &
                        a.getSurname().equals(author.getSurname())
                        & a.getPatronymic().equals(author.getPatronymic())) {
                    return Optional.of(a);
                }
            }
        }
        return Optional.empty();
    }
}
