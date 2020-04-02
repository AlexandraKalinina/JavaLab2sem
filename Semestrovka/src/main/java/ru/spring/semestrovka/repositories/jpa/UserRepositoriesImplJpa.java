package ru.spring.semestrovka.repositories.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.UserRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoriesImplJpa implements UserRepositories {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> getUserByLogin(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByConfirmCode(String confirmCode) {
        return Optional.empty();
    }

    @Override
    public void update(String confirm) {

    }

    @Override
    public Optional<User> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void save(User object) {
        entityManager.persist(object);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
