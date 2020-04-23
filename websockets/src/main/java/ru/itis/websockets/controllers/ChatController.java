package ru.itis.websockets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.websockets.dto.UserDto;
import ru.itis.websockets.services.CookieService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class ChatController {

    @Autowired
    CookieService cookieService;

    @GetMapping("/chat")
    public String getChatPage(Model model, ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        UserDto userDto = cookieService.getToken(request);
        if (userDto != null) {
            model.addAttribute("userId", userDto.getId());
            return "chat";
        } else return "signIn";
    }
}
