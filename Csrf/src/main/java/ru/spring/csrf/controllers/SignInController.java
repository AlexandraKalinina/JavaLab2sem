package ru.spring.csrf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.csrf.dto.SignInDto;
import ru.spring.csrf.model.User;
import ru.spring.csrf.service.SignInService;

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
    public ModelAndView signIn(@ModelAttribute(name = "user") SignInDto signInDto) {
        Optional<User> user = signInService.signIn(signInDto);
        if (user.isPresent()) {
            return new ModelAndView("redirect:/profile");
        } else return new ModelAndView("redirect:/signIn");
    }
}
