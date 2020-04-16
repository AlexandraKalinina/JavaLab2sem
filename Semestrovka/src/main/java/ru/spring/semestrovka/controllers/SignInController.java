package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.TokenDto;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.service.SignInService;
import ru.spring.semestrovka.service.UserService;

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
        HttpSession session = req.getSession();
        TokenDto tokenDto = signInService.signIn(signInDto);
        if (tokenDto != null) {
            if (session.getAttribute("user") == null) {
                Optional<User> user = userService.getUserBySignInDto(signInDto);
                session.setAttribute("user", user);
            }
            Cookie cookie = new Cookie("token", tokenDto.getToken());
            resp.addCookie(cookie);
            return new ModelAndView("redirect:/profile/");
        } else return new ModelAndView("redirect:/signIn");
        //TO DO: cookie
    }
}
