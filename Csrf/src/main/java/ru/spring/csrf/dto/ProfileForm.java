package ru.spring.csrf.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProfileForm {
    @Email
    @NotNull
    private String email;

    @NotNull
    @Min(value = 0)
    private Integer age;

}
