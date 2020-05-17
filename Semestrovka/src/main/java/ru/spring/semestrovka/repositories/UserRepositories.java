package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.dto.InformationDto;
import ru.spring.semestrovka.model.User;

import java.util.Optional;

public interface UserRepositories extends CrudRepositories<User, Long> {
    Optional<User> getUserByLogin(String email);
    Optional<User> findByConfirmCode(String confirmCode);
    void update(String confirm);

}
