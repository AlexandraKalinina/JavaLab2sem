package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.dto.SignInDto;
import ru.spring.semestrovka.dto.TokenDto;
import ru.spring.semestrovka.dto.UserDto;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.service.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SignInController {
    @Autowired
    private SignInService signInService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signInCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn");
        return modelAndView;
    }
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(@ModelAttribute(name = "user") SignInDto signInDto, HttpServletRequest req) {
        HttpSession session = req.getSession();
        TokenDto tokenDto = signInService.signIn(signInDto);
        if (tokenDto != null) {
            if (session.getAttribute("user") == null) {
                session.setAttribute("user", signInDto);
            }
            return new ModelAndView("redirect:/profile");
        } else return new ModelAndView("redirect:/signIn");
        //TO DO: cookie
    }
}
