package ru.spring.stompproject.services;

import java.util.Map;

public interface MessageCreate {
    String createMessageFtl(String name, Map<String, Object> root);
}
