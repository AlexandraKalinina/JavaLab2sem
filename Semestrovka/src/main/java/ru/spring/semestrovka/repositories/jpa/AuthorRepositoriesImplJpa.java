package ru.spring.semestrovka.repositories.jpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.AuthorRepositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Component(value = "authorRepositories")
public class AuthorRepositoriesImplJpa implements AuthorRepositories {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    private static final String HQL_SELECT_ALL = "from Author";

    //language=HQL
    private static final String HQL_SELECT_BY_SNP = "from Author a where a.name =:name and a.surname =:surname and a.patronymic =:patronymic";


    //language=HQL
    private static final String HQL_SELECT_BY_SURNAME = "from Author a where a.surname =:surname";


    @Override
    @Transactional
    public Optional<Author> getAuthorBySNP(String name, String surname, String patronymic) {
        try {
            Author author = (Author) entityManager.createQuery(HQL_SELECT_BY_SNP)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("patronymic", patronymic)
                    .getSingleResult();
            return Optional.ofNullable(author);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Author> getAuthorsBySurname(String surname) {
        List authors = entityManager.createQuery(HQL_SELECT_BY_SURNAME)
                .setParameter("surname", surname)
                .getResultList();
        return authors;
    }

    @Override
    @Transactional
    public void update(Author author) {
        entityManager.merge(author);
    }

    @Override
    @Transactional
    public Optional<Author> find(Long id) {
        try {
            Author author = entityManager.find(Author.class, id);
            return Optional.ofNullable(author);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(Author object) {
        entityManager.persist(object);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
    }

    @Override
    @Transactional
    public List<Author> findAll() {
        return entityManager.createQuery(HQL_SELECT_ALL).getResultList();
    }
}
