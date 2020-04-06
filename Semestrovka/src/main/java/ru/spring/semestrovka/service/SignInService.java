package ru.spring.semestrovka.service;

import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
