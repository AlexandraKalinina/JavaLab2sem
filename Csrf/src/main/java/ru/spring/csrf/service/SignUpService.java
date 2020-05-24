package ru.spring.csrf.service;

import ru.spring.csrf.dto.SignUpDto;

public interface SignUpService {
    void signUp(SignUpDto form);
}
