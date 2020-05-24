package ru.spring.csrf.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String password;
    private String email;
}
