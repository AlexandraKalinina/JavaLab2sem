package ru.spring.csrf.repositories;

import ru.spring.csrf.model.User;

import java.util.Optional;

public interface UserRepositories extends CrudRepositories<User, Long> {
    Optional<User> getUserByLogin(String email);

}
