package ru.itis.websockets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.websockets.model.Role;
import ru.itis.websockets.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private Role role;
    public static UserDto from(User user) {
        return UserDto.builder().id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
