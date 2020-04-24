package ru.itis.websockets.services;

import ru.itis.websockets.dto.SignInDto;
import ru.itis.websockets.dto.SignUpDto;
import ru.itis.websockets.dto.UserDto;
import ru.itis.websockets.model.User;

import java.util.Optional;

public interface UserService {
    boolean checkUserByPassword(String password1, String password2);
    Optional<User> getUserBySignUpDto(SignUpDto signUpDto);
    Optional<User> getUserBySignInDto(SignInDto signInDto);
}
