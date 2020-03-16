package ru.spring.project.hw3.service;

import freemarker.template.Configuration;
import ru.spring.project.hw3.dto.SignUpDto;
import ru.spring.project.hw3.model.State;
import ru.spring.project.hw3.model.User;
import ru.spring.project.hw3.repositories.UserRepositories;


import java.util.Optional;
import java.util.UUID;

public class SignUpServiceImpl implements SignUpService {
    private UserRepositories userRepositories;

    public SignUpServiceImpl(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public SignUpServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    private EmailService emailService;
    @Override
    public void signUp(SignUpDto form, Configuration cfg) {
        User user = User.builder()
                .name(form.getName())
                .password(form.getPassword())
                .email(form.getEmail())
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();
        Optional<User> user1 = userRepositories.getUserByLogin(user.getEmail());
        if (!user1.isPresent()) {
            userRepositories.save(user);
        }
        emailService.sendEmail("Confirm", user.getEmail(),
                user.getConfirmCode(), user.getName(), cfg);
    }

}
