package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.model.Role;
import ru.spring.semestrovka.model.State;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.util.Optional;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean signUp(SignUpDto form) {
        User user = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(bCryptPasswordEncoder.encode(form.getPassword()))
                .state(State.NOT_CONFIRMED)
                .role(Role.USER)
                .confirmCode(UUID.randomUUID().toString())
                .build();
        Optional<User> user1 = userRepositories.getUserByLogin(user.getEmail());
        if (!user1.isPresent()) {
            userRepositories.save(user);
            emailService.sendEmail("Confirm", user.getEmail(),
                    user.getConfirmCode(), user.getName(), "email_template.ftl");
            return true;
        } else return false;
    }
}
