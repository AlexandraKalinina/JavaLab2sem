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

@Component(value = "roomRepositories")
public class RoomRepositoriesImpl implements RoomRepositories {

    @PersistenceContext
    private EntityManager entityManager;

    //language=HQL
    private static final String HQL_SELECT_ALL = "FROM Room";

    @Override
    @Transactional
    public Optional<Room> find(Long id) {
        try {
            Room room = entityManager.find(Room.class, id);
            return Optional.ofNullable(room);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }    }

    @Override
    @Transactional
    public void save(Room object) {
        entityManager.persist(object);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Room room = entityManager.find(Room.class, id);
        entityManager.remove(room);
    }

    @Override
    @Transactional
    public List<Room> findAll() {
        return entityManager.createQuery(HQL_SELECT_ALL).getResultList();
    }
}
