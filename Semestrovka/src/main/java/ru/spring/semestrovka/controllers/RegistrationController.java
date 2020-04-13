package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.service.SignUpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    private SignUpService signUpService;

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
            session.setAttribute("user", signUpDto);
        } else return new ModelAndView("redirect:/registration");
        return null;
    }
}
