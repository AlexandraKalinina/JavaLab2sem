package ru.spring.semestrovka.repositories.jpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.repositories.BookRepositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component(value = "bookRepositories")
public class BookRepositoriesImplJpa implements BookRepositories {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    private static final String HQL_SELECT_BY_PATH = "from Book b where b.text = :text";

    //language=HQL
    private static final String HQL_SELECT_ALL = "from Book";

    @Override
    @Transactional
    public Optional<Book> getBookByPath(String path) {
        Book book = null;
        try {
            book = (Book) entityManager.createQuery(HQL_SELECT_BY_PATH)
                    .setParameter("text", path).getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(book);
    }

    @Override
    @Transactional
    public Optional<Book> find(Long id) {
        try {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(Book object) {
        entityManager.persist(object);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        return entityManager.createQuery(HQL_SELECT_ALL).getResultList();
    }
}
