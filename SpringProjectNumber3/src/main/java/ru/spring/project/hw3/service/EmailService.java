package ru.spring.project.hw3.service;

import freemarker.template.Configuration;

public interface EmailService {
    void sendEmail(String subject, String email, String confirmCode, String userName, Configuration cfg) ;
}
