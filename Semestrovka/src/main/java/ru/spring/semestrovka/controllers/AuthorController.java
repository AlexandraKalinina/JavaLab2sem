package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.service.AuthorService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/sequenceAuthor", method = RequestMethod.GET)
    public ModelAndView book(@RequestParam("id") Long id)  {
        ModelAndView modelAndView = new ModelAndView();
        //в автор сервис кидается айди получаю список книг автора
        List<Book> books = authorService.getBooks(id);
        Author author = authorService.find(id).get();
        modelAndView.addObject("books", books);
        modelAndView.addObject("authors", Collections.singletonList(author));
        modelAndView.setViewName("authors");
        return modelAndView;
    }
}
