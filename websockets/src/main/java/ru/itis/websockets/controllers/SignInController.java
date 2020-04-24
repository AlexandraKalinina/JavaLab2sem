package ru.itis.websockets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.websockets.dto.SignInDto;
import ru.itis.websockets.dto.TokenDto;
import ru.itis.websockets.model.User;
import ru.itis.websockets.services.SignInService;
import ru.itis.websockets.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SignInController {
    @Autowired
    private SignInService signInService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signInCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn");
        return modelAndView;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(@ModelAttribute(name = "user") SignInDto signInDto, HttpServletRequest req,
                               HttpServletResponse resp) {
        TokenDto tokenDto = signInService.signIn(signInDto);
        if (tokenDto != null) {
            Cookie cookie = new Cookie("token", tokenDto.getToken());
            resp.addCookie(cookie);
            return new ModelAndView("redirect:/room");
        } else return new ModelAndView("redirect:/signIn");
    }
}
