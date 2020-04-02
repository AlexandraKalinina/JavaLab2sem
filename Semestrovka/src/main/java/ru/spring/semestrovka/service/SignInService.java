package ru.spring.semestrovka.service;

import ru.spring.semestrovka.dto.UserDto;
import ru.spring.semestrovka.model.User;

import java.util.Optional;

public interface SignInService {
    Optional<User> signIn(UserDto userDto);
}
