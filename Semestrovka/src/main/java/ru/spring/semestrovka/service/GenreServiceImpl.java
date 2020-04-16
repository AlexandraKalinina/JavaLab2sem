package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;
import ru.spring.semestrovka.repositories.GenreRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    @Qualifier("genreRepositories")
    public GenreRepositories genreRepositories;

    @Override
    public List<Genre> readFile(String name, Book book) {
        Genre current_genre = Genre.builder()
                .name(name)
                .book(book)
                .build();
        List<Genre> genres = getGenre(current_genre);
        if (genres.size() == 0) {
            genreRepositories.save(current_genre);
            return getGenre(current_genre);
        }
        else return genres;
    }

    @Override
    public List<Genre> getGenre(Genre genre) {
        List<Genre> genres = genreRepositories.getGenreByIdBook(genre.getBook());
        if (genres.size() != 0) {
            for (Genre g : genres) {
                if (g.getName().equals(genre.getName())) {
                    return genres;
                }
            }
        }
        return new ArrayList<>();
    }

}
