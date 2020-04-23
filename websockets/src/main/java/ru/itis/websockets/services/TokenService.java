package ru.itis.websockets.services;

import ru.itis.websockets.dto.UserDto;

public interface TokenService {
    UserDto parseToken(String token);
}
