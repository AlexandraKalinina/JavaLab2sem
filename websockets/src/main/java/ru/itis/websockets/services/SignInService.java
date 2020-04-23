package ru.itis.websockets.services;

import ru.itis.websockets.dto.SignInDto;
import ru.itis.websockets.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
