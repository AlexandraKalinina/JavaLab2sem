package ru.spring.project.hw3.service;

import freemarker.template.Configuration;
import ru.spring.project.hw3.dto.SignUpDto;



public interface SignUpService {
    void signUp(SignUpDto form, Configuration cfg);
}
