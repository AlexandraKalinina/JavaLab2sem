package ru.spring.stompproject.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonHelperImpl implements JsonHelper {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getBodyByJson(String message) throws JsonProcessingException {
        Map<String, Object> map = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map1 = objectMapper.convertValue(map.get("json"), new TypeReference<Map<String, Object>>() {
        });
        return (String) map1.get("body");
    }
}
