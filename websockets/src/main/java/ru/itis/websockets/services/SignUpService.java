package ru.itis.websockets.services;

import ru.itis.websockets.dto.SignUpDto;

public interface SignUpService {
    boolean signUp(SignUpDto form);
}
