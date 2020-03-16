package ru.spring.project.hw3.service;

import freemarker.template.Configuration;

import java.util.Map;

public interface MessageCreate {
    String createMessageFtl(String name, Map<String, Object> root, Configuration cfg);
}
