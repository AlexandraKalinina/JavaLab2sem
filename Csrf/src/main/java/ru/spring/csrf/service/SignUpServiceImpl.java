package ru.spring.csrf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.csrf.dto.SignUpDto;
import ru.spring.csrf.model.Role;
import ru.spring.csrf.model.User;
import ru.spring.csrf.repositories.UserRepositories;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepositories userRepositories;


    @Override
    public void signUp(SignUpDto form) {
        User user = User.builder()
                .name(form.getName())
                .password(bCryptPasswordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .role(Role.USER)
                .build();
        Optional<User> user1 = userRepositories.getUserByLogin(form.getEmail());
        if (!user1.isPresent()) {
            userRepositories.save(user);
        }
    }
}
