package ru.spring.semestrovka.controllers;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.service.AuthorService;
import java.util.Collections;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/sequenceAuthor", method = RequestMethod.GET)
    public ModelAndView book(@RequestParam("id") Long id)  {
        ModelAndView modelAndView = new ModelAndView();
        //в автор сервис кидается айди получаю список книг автора
        /*List<Book> books = authorService.getBooks(id);*/
        Author author = authorService.find(id).get();
        Hibernate.initialize(author.getBooks());
        modelAndView.addObject("books", author.getBooks());
        modelAndView.addObject("authors", Collections.singletonList(author));
        modelAndView.setViewName("authors");
        return modelAndView;
    }
}
