package ru.spring.stompproject.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonHelper {
    String getBodyByJson(String message) throws JsonProcessingException;
}
