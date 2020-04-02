package ru.spring.semestrovka.service;

import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.model.User;

import java.util.Optional;

public interface UserService {
    boolean checkUserByPassword(String password1, String password2);
    Optional<User> getUserBySignUpDto(SignUpDto signUpDto);
}
