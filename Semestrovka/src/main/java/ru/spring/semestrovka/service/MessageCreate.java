package ru.spring.semestrovka.service;

import java.util.Map;

public interface MessageCreate {
    String createMessageFtl(String name, Map<String, Object> root);
}
