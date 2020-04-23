package ru.itis.websockets.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.websockets.dto.UserDto;
import ru.itis.websockets.model.Role;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public UserDto parseToken(String token) {
        Claims claims;
        try {
            // выполняю парсинг токена со своим секретным ключом
            claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }
        UserDto userDto = UserDto.builder()
                .id(Long.parseLong(claims.get("sub", String.class)))
                .email(claims.get("name", String.class))
                .role(Role.valueOf(claims.get("role", String.class)))
                .build();
        return userDto;
    }
}
