package ru.itis.websockets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoomController {
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public ModelAndView roomCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("room");
        return modelAndView;
    }
}
