package ru.spring.stompproject.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
