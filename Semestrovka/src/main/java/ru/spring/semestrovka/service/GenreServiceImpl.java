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

import java.util.*;

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
                .books(new HashSet<>())
                .build();
        Optional<Genre> genre = getGenre(current_genre);

        if (!genre.isPresent()) {

            /*current_genre.setBooks(Collections.singleton(book));*/
            /*genreRepositories.update(current_genre);*/
            /*book.setGenres(Collections.singleton(current_genre));*/
            current_genre.addBook(book);
            genreRepositories.save(current_genre);
            /*bookRepositories.update(book);*/
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
