package ru.spring.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.files.dto.SignUpDto;
import ru.spring.files.model.State;
import ru.spring.files.model.User;
import ru.spring.files.repositories.UserRepositories;
import java.util.Optional;
import java.util.UUID;


public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private EmailService emailService;

    @Override
    public void signUp(SignUpDto form) {
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
                user.getConfirmCode(), user.getName(), "email_template.ftl");
    }

}
