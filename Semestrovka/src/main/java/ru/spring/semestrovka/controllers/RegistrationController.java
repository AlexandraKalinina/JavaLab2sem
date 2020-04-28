package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.service.SignUpService;
import ru.spring.semestrovka.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserService userService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute(name = "user") SignUpDto signUpDto, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (signUpService.signUp(signUpDto)) {
            Optional<User> user = userService.getUserBySignUpDto(signUpDto);
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/profile");
        } else return new ModelAndView("redirect:/registration");
        //сделать страницу с надписью о том, что нужно подтвердить эмайл и ссылку на авторизацию
    }
}
