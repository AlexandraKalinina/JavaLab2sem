package ru.spring.semestrovka.service;

import ru.spring.semestrovka.dto.InformationDto;
import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.model.User;

import java.util.Optional;

public interface UserService {
    boolean checkUserByPassword(String password1, String password2);
    Optional<User> getUserBySignUpDto(SignUpDto signUpDto);
    Optional<User> getUserBySignInDto(SignInDto signInDto);

    InformationDto getInformationByUser(Long userId);
}
