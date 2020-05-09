package ru.spring.stompproject.services;

public interface EmailService {
    void sendEmail(String subject, String email, String userName, String ftl, String fileName);
}
