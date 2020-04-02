package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.dto.SignUpDto;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.service.ConfirmService;
import ru.spring.semestrovka.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @Autowired
    private UserService userService;

    //@PreAuthorize("permitAll()")
    @RequestMapping(value = "/confirm/*", method = RequestMethod.GET)
    public ModelAndView confirmCreate(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm");
        if (session.getAttribute("user") != null) {
            SignUpDto signUpDto = (SignUpDto) session.getAttribute("user");
            confirmService.updateSate(signUpDto.getEmail());
        }
        return modelAndView;
    }
    //@PreAuthorize("permitAll()")
    @RequestMapping(value = "/confirm/redirect", method = RequestMethod.GET)
    public String redirectWithUsingRedirectPrefix(HttpSession session) {
        if (session.getAttribute("user") != null) {
            SignUpDto signUpDto = (SignUpDto) session.getAttribute("user");
            Optional<User> user = userService.getUserBySignUpDto(signUpDto);
            if (user.isPresent()) {
                return "redirect:/profile/" + user.get().getId();
            } else {
                return "redirect:/registration";
            }
        }
        return "redirect:/signIn";
    }

}
