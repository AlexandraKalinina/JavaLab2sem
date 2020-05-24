package ru.spring.csrf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.csrf.dto.SignInDto;
import ru.spring.csrf.model.User;
import ru.spring.csrf.repositories.UserRepositories;

import java.util.Optional;

@Service
public class SIgnInServiceImpl implements SignInService {
    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private UserService userService;

    @Override
    public Optional<User> signIn(SignInDto signInDto) {
        String login = signInDto.getEmail();
        String password = signInDto.getPassword();
        Optional<User> user = userRepositories.getUserByLogin(login);
        if (user.isPresent() && userService.checkUserByPassword(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
}
