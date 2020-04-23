package ru.itis.websockets.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.websockets.dto.TokenDto;
import ru.itis.websockets.dto.UserDto;
import ru.itis.websockets.model.Role;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieServiceImpl implements CookieService {

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDto getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        UserDto userDto = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")) {
                    token = cookies[i].getValue();
                    return tokenService.parseToken(token);
                }
            }
        } else throw new IllegalArgumentException("Cookies are empty");
        return userDto;
    }
}
