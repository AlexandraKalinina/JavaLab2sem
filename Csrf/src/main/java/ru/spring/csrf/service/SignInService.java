package ru.spring.csrf.service;

import ru.spring.csrf.dto.SignInDto;
import ru.spring.csrf.model.User;

import java.util.Optional;

public interface SignInService {
    Optional<User> signIn(SignInDto signInDto);
}
