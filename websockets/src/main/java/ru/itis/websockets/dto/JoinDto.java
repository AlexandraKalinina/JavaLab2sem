package ru.itis.websockets.dto;

import lombok.Data;

@Data
public class JoinDto {
    private Long userId;
    private Long room;
}
