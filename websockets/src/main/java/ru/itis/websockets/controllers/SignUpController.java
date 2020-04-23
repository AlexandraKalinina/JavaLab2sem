package ru.itis.websockets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.websockets.dto.SignUpDto;
import ru.itis.websockets.model.User;
import ru.itis.websockets.services.SignUpService;
import ru.itis.websockets.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signInCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signUp");
        return modelAndView;
    }
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView signIn(@ModelAttribute(name = "user") SignUpDto signUpDto) {
        if (signUpService.signUp(signUpDto)) {
            Optional<User> user = userService.getUserBySignUpDto(signUpDto);
            return new ModelAndView("redirect:/signIn");
        } else return new ModelAndView("redirect:/signUp");
    }
}
