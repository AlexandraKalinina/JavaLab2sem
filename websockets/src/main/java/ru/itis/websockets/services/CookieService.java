package ru.itis.websockets.services;

import ru.itis.websockets.dto.TokenDto;
import ru.itis.websockets.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public interface CookieService {
    UserDto getToken(HttpServletRequest request);
}
