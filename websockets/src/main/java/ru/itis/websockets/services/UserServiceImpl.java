package ru.itis.websockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.websockets.dto.SignInDto;
import ru.itis.websockets.dto.SignUpDto;
import ru.itis.websockets.dto.UserDto;
import ru.itis.websockets.model.User;
import ru.itis.websockets.repositories.UserRepositories;

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
