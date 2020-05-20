package ru.spring.semestrovka.repositories.jpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;
import ru.spring.semestrovka.repositories.GenreRepositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component(value = "genreRepositories")
public class GenreRepositoriesImplJpa implements GenreRepositories {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    private static final String HQL_SELECT_ALL = "from Genre";

    //language=HQL
    private static final String HQL_SELECT_BY_NAME = "from Genre g where g.name = :name";

    /*@Override
    @Transactional
    public List<Genre> getGenreByIdBook(Book book) {
        List genres = entityManager.createQuery(HQL_SELECT_BY_ID_BOOK)
                .setParameter("book", book)
                .getResultList();
        return genres;
    }*/

    @Override
    @Transactional
    public Optional<Genre> find(Long id) {
        try {
            Genre genre = entityManager.find(Genre.class, id);
            return Optional.ofNullable(genre);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(Genre object) {
        entityManager.persist(object);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Genre genre = entityManager.find(Genre.class, id);
        entityManager.remove(genre);
    }

    @Override
    @Transactional
    public List<Genre> findAll() {
        return entityManager.createQuery(HQL_SELECT_ALL).getResultList();
    }

    @Override
    public Optional<Genre> getGenreByName(String name) {
        try {
            Genre genre = (Genre) entityManager.createQuery(HQL_SELECT_BY_NAME)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.ofNullable(genre);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void update(Genre genre) {
        entityManager.merge(genre);
    }
}
