package ru.spring.semestrovka.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProfileController {

   /* @PreAuthorize("isAuthenticated()")*/
   // @RequestMapping(value = "/profile/{id-user}", method = RequestMethod.GET)
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        //public ModelAndView loadFile(@PathVariable("id-user") String id) {
        ModelAndView modelAndView = new ModelAndView();
        /*modelAndView.addObject("user", userDetails.getUser());*/
        modelAndView.setViewName("profile");
        return modelAndView;
    }

}
