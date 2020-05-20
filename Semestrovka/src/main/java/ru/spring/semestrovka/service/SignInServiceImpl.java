package ru.spring.semestrovka.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.TokenDto;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.util.Optional;
@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;


    @Override
    public TokenDto signIn(SignInDto signInDto) {
        String login = signInDto.getEmail();
        String password = signInDto.getPassword();
        Optional<User> user = userRepositories.getUserByLogin(login);
        if (user.isPresent()) {
            User current_user = user.get();
            if (userService.getConfirmed(user.get())) {
                if (userService.checkUserByPassword(password, user.get().getPassword())) {
                    String token = Jwts.builder()
                            .setSubject(current_user.getId().toString()) // id пользователя
                            .claim("name", current_user.getEmail()) // имя
                            .claim("role", current_user.getRole().name()) // роль
                            .signWith(SignatureAlgorithm.HS256, secret) // подписываем его с нашим secret
                            .compact(); // преобразовали в строку
                    return new TokenDto(token);
                } else throw new AccessDeniedException("Wrong email/password");
            } else throw new AccessDeniedException("Registration not confirmed");
        } else throw new AccessDeniedException("User not found");
    }
}
