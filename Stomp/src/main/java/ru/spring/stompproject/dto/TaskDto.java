package ru.spring.stompproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {
    private String command;
    private String queueName;
    private String data;
}
