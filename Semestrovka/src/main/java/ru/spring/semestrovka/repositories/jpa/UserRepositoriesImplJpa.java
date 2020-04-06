package ru.spring.semestrovka.repositories.jpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.model.State;
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

    //language=HQL
    private static final String HQL_SELECT_BY_EMAIL = "from User u where u.email = :email";

    //language=HQL
    private static final String HQL_SELECT_BY_CONFIRM = "from User u where u.confirmCode = :confirmCode";

    //language=HQL
    private static final String HQL_SELECT_ALL = "from User";


    @Override
    @Transactional
    public Optional<User> getUserByLogin(String email) {
        try {
            User user = (User) entityManager.createQuery(HQL_SELECT_BY_EMAIL)
                    .setParameter("email", email).getSingleResult();
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<User> findByConfirmCode(String confirmCode) {
        try {
            User user = (User) entityManager.createQuery(HQL_SELECT_BY_CONFIRM)
                    .setParameter("confirmCode", confirmCode).getSingleResult();
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void update(String confirmCode) throws EmptyResultDataAccessException {
        User user = (User) entityManager.createQuery(HQL_SELECT_BY_CONFIRM)
                .setParameter("confirmCode", confirmCode).getSingleResult();
        user.setState(State.valueOf("CONFIRMED"));
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public Optional<User> find(Long id) {
        try {
            User user = entityManager.find(User.class, id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(User object) {
        entityManager.persist(object);
    }

    @Override
    @Transactional
    public void delete(Long id) throws UsernameNotFoundException {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return entityManager.createQuery(HQL_SELECT_ALL, User.class).getResultList();
    }
}

