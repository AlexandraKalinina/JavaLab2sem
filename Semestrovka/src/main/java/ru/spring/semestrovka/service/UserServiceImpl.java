package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public boolean checkUserByPassword(String password1, String password2) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password1, password2);
    }

    @Override
    public Optional<User> getUserBySignUpDto(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        return userRepositories.getUserByLogin(email);
    }

    @Override
    public Optional<User> getUserBySignInDto(SignInDto signInDto) {
        String email = signInDto.getEmail();
        return userRepositories.getUserByLogin(email);
    }
}
