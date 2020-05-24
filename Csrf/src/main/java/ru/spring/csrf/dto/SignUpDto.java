package ru.spring.csrf.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {

    @NotEmpty
    private String name;

    @Size(min = 6, max = 28)
    private String password;

    @Email
    @NotEmpty
    private String email;
}
