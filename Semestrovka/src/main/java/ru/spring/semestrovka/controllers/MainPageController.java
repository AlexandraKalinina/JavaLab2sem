package ru.spring.semestrovka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public ModelAndView library() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("library");
        return modelAndView;
    }
    @RequestMapping(value = "/library/signUp", method = RequestMethod.GET)
    public ModelAndView registration() {
        return new ModelAndView("redirect:/registration");
    }
    @RequestMapping(value = "/library/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        return new ModelAndView("redirect:/signIn");
    }
}
