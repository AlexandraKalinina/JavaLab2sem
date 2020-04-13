package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public boolean updateSate(String email) {
        Optional<User> userOptional = userRepositories.getUserByLogin(email);
        if (userOptional.isPresent()) {
            userRepositories.update(userOptional.get().getConfirmCode());
            return true;
        }
        return false;
    }
}
