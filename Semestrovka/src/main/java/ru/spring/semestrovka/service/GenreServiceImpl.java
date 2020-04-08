package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.semestrovka.model.Genre;
import ru.spring.semestrovka.repositories.GenreRepositories;

import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService {

    @Autowired
    public GenreRepositories genreRepositories;

    @Override
    public Optional<Genre> readFile(String name, Long id_book) {
        Genre current_genre = Genre.builder()
                .name(name)
                .id_book(id_book)
                .build();
        Optional<Genre> genre = getGenre(current_genre);
        if (!genre.isPresent()) {
            genreRepositories.save(current_genre);
            return getGenre(current_genre);
        }
        else return genre;
    }

    @Override
    public Optional<Genre> getGenre(Genre genre) {
        List<Genre> genres = genreRepositories.getGenreByIdBook(genre.getId_book());
        if (genres.size() != 0) {
            for (Genre g : genres) {
                if (g.getName().equals(genre.getName())) {
                    return Optional.of(g);
                }
            }
        }
        return Optional.empty();
    }

}
