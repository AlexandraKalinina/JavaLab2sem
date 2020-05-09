package ru.spring.stompproject.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String nameQueue;
    private String json;
}
