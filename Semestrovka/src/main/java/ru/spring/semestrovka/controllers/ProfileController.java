package ru.spring.semestrovka.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProfileController {

   // @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/{id-user}", method = RequestMethod.GET)
    public ModelAndView loadFile(@PathVariable("id-user") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;
    }

}
