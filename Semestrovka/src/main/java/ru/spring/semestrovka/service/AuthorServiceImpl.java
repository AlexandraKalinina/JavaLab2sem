package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.repositories.AuthorRepositories;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepositories authorRepositories;

    @Override
    public Optional<Author> readFile(String name, String surname, String patronymic, Long id_book) {
        Author current_author = Author.builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .id_book(id_book)
                .build();
        Optional<Author> author = getAuthor(current_author);
        if (!author.isPresent()) {
            authorRepositories.save(current_author);
            return getAuthor(current_author);
        } else return author;
    }


    @Override
    public Optional<Author> getAuthor(Author author) {
        List<Author> authors = authorRepositories.getAuthorsByIdBook(author.getId_book());
        if (authors.size() != 0) {
            for (Author a: authors) {
                if (a.getName().equals(author.getName()) &&
                        a.getSurname().equals(author.getName())
                        && a.getPatronymic().equals(author.getPatronymic())) {
                    return Optional.of(a);
                }
            }
        }
        return Optional.empty();
    }
}
