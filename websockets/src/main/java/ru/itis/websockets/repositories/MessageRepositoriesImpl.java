package ru.itis.websockets.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.websockets.model.Message;
import ru.itis.websockets.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component(value = "messageRepositories")
public class MessageRepositoriesImpl implements MessageRepositories {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    private static final String HQL_SELECT_ALL = "from Message";

    //language=HQL
    private static final String HQL_SELECT_BY_ID_BOOK = "from Message r where r.room = room";

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
        Message message = entityManager.find(Message.class, id);
        entityManager.remove(message);
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        return entityManager.createQuery(HQL_SELECT_ALL).getResultList();
    }

    @Override
    public List<Message> getListByIdRoom(Room room) {
        List messages = entityManager.createQuery(HQL_SELECT_BY_ID_BOOK)
                .setParameter("room", room)
                .getResultList();
        return messages;
    }
}
