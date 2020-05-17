package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.semestrovka.dto.InformationDto;
import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.BookRepositories;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private BookRepositories bookRepositories;

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

    @Override
    public InformationDto getInformationByUser(Long userId) {
        Optional<User> user = userRepositories.find(userId);
        InformationDto info = null;
        if (user.isPresent()) {
            Long sum = bookRepositories.getSumSizeBookOwner(user.get());
            info = InformationDto.builder()
                    .name(user.get().getName())
                    .size(sum)
                    .build();
            return info;
        } else throw new UsernameNotFoundException("User not found");
    }
}
