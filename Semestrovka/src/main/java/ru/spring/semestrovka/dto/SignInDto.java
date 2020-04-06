package ru.spring.semestrovka.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String password;
    private String email;
}
