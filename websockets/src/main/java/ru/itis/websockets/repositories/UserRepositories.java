package ru.itis.websockets.repositories;

import ru.itis.websockets.model.User;

import java.util.Optional;

public interface UserRepositories extends CrudRepositories<User, Long> {
    Optional<User> getUserByLogin(String email);
}
