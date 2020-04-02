package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.semestrovka.dto.UserDto;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private UserService userService;

    @Override
    public Optional<User> signIn(UserDto userDto) {
        String login = userDto.getEmail();
        String password = userDto.getPassword();
        Optional<User> user = userRepositories.getUserByLogin(login);
        if (user.isPresent() && userService.checkUserByPassword(password, user.get().getHashPassword())) {
            return user;
        }
        return Optional.empty();
    }
}
