package ru.spring.project.hw3.service;

import ru.spring.project.hw3.model.State;
import ru.spring.project.hw3.model.User;
import ru.spring.project.hw3.repositories.UserRepositories;
import ru.spring.project.hw3.repositories.UserRepositoriesImpl;

import java.util.Optional;

public class ConfirmServiceImpl implements ConfirmService {

    private UserRepositories userRepositories;

    public ConfirmServiceImpl(UserRepositoriesImpl userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public boolean confirm(String confirmCode) {
        Optional<User> userOptional = userRepositories.findByConfirmCode(confirmCode);
        if (userOptional.isPresent()) {
            userRepositories.update(confirmCode);
            return true;
        }
        return false;

    }
}
