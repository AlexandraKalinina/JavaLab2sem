package ru.spring.csrf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.csrf.dto.SignUpDto;
import ru.spring.csrf.service.SignUpService;

import javax.validation.Valid;

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
    public ModelAndView registration(@Valid @ModelAttribute(name = "user") SignUpDto signUpDto, BindingResult result) {
        if (!result.hasErrors()) {
            signUpService.signUp(signUpDto);
            return new ModelAndView("/signIn");
        }
        return new ModelAndView("/registration");
    }
}
