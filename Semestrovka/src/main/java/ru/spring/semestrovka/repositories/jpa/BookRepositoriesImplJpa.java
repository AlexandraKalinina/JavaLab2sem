package ru.spring.semestrovka.repositories.jpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.BookRepositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    //language=HQL
    private static final String HQL_SELECT_BY_NAME = "from Book b where b.name =:name";

    //language=HQL
    private static final String HQL_SELECT_BY_OWNER = "select SUM(b.size / 1024 / 1024) from Book b where b.owner =:owner";

    @Override
    @Transactional
    public Optional<Book> getBookByPath(String path) {
        try {
            Book book = (Book) entityManager.createQuery(HQL_SELECT_BY_PATH)
                    .setParameter("text", path).getSingleResult();
            return Optional.ofNullable(book);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public List<Book> getBooksByName(String name) {


       List books = entityManager.createQuery(HQL_SELECT_BY_NAME)
                .setParameter("name", name)
                .getResultList();
        String s = "";
        return books;
    }

    @Override
    public Long getSumSizeBookOwner(User user) {
        Long sum = (Long) entityManager.createQuery(HQL_SELECT_BY_OWNER)
                .setParameter("owner", user)
                .getSingleResult();
        return sum;
    }

    @Override
    @Transactional
    public void update(Book book) {

        entityManager.merge(book);
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
