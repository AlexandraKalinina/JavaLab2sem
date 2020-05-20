package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;
import ru.spring.semestrovka.repositories.BookRepositories;
import ru.spring.semestrovka.repositories.GenreRepositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    @Qualifier("genreRepositories")
    public GenreRepositories genreRepositories;

    @Autowired
    private BookRepositories bookRepositories;

    @Override
    @Transactional
    public void readFile(String name, Book book) {
        Genre current_genre = Genre.builder()
                .name(name)
                .build();
        Optional<Genre> genre = getGenre(current_genre);
        if (genre.isPresent()) {
            if (!checkGenre(genre.get(),book)) {
                book.setGenres(Collections.singletonList(genre.get()));
                bookRepositories.update(book);
            }
        } else {
           /* current_genre.setBooks(Collections.singletonList(book));
            genreRepositories.update(current_genre);*/
            genreRepositories.save(current_genre);
            book.setGenres(Collections.singletonList(current_genre));
            bookRepositories.update(book);
            /*genreRepositories.save(current_genre);*/
        }
    }

    @Override
    public Optional<Genre> getGenre(Genre genre) {
        return genreRepositories.getGenreByName(genre.getName());
    }

    @Override
    public boolean checkGenre(Genre genre, Book book) {
        return genre.getBooks().contains(book);
    }

}
