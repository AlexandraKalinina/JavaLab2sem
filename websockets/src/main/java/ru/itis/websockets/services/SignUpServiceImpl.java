package ru.itis.websockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.websockets.dto.SignUpDto;
import ru.itis.websockets.model.Role;
import ru.itis.websockets.model.User;
import ru.itis.websockets.repositories.UserRepositories;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public boolean signUp(SignUpDto form) {
        User user = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(bCryptPasswordEncoder.encode(form.getPassword()))
                .role(Role.USER)
                .build();
        Optional<User> user1 = userRepositories.getUserByLogin(user.getEmail());
        if(!user1.isPresent()) {
            userRepositories.save(user);
            return true;
        } else return false;
    }
}
