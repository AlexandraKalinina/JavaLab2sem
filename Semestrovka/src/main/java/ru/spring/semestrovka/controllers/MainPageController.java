package ru.spring.semestrovka.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class MainPageController {
    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public ModelAndView library() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("library");
        return modelAndView;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/library/signUp", method = RequestMethod.GET)
    public ModelAndView registration() {
        return new ModelAndView("redirect:/registration");
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/library/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        return new ModelAndView("redirect:/signIn");
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/library/book", method = RequestMethod.GET)
    public ModelAndView book() {
        return new ModelAndView("redirect:/book");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/searchBook")
    public ModelAndView search() {
        return new ModelAndView("redirect:/search");
    }
}
