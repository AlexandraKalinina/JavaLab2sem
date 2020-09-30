package ru.itis.websockets.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.WebUtils;
import ru.itis.websockets.dto.UserDto;
import ru.itis.websockets.services.CookieService;

import java.util.Map;

@Component
public class AuthHandshakeHandler implements HandshakeHandler {

    @Autowired
    private DefaultHandshakeHandler defaultHandshakeHandler;

    @Autowired
    private CookieService cookieService;

    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws HandshakeFailureException {
        ServletServerHttpRequest request = (ServletServerHttpRequest)serverHttpRequest;
        //String cookie = WebUtils.getCookie(request.getServletRequest(), "token").getValue();
        UserDto userDto = cookieService.getToken(request.getServletRequest());
        if (userDto != null) {
            map.put("userId", userDto.getId());
            return defaultHandshakeHandler.doHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, map);
        } else {
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }

    }
}