package ru.itis.websockets.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Long userId;
    private Long roomId;
    private String text;
}
