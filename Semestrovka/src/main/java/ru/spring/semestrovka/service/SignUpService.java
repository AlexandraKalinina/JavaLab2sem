package ru.spring.semestrovka.service;

import ru.spring.semestrovka.dto.SignUpDto;

public interface SignUpService {
    boolean signUp(SignUpDto form);
}
