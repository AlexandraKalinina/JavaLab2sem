package ru.spring.semestrovka.repositories.jpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Message;
import ru.spring.semestrovka.repositories.MessageRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component(value = "messageRepositories")
public class MessageRepositoriesImplJpa implements MessageRepositories {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    private static final String HQL_SELECT_ALL = "from Message";

    //language=HQL
    private static final String HQL_SELECT_BY_ID_BOOK = "from Message m where m.book =:book";

    @Override
    @Transactional
    public Optional<Message> find(Long id) {
        try {
            Message message = entityManager.find(Message.class, id);
            return Optional.ofNullable(message);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(Message object) {
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
    public List<Message> findAll() {
        return entityManager.createQuery(HQL_SELECT_ALL).getResultList();
    }

    @Override
    @Transactional
    public List<Message> getListByIdBook(Book book) {
        List messages = entityManager.createQuery(HQL_SELECT_BY_ID_BOOK)
                .setParameter("book", book)
                .getResultList();
        return messages;
    }
}
