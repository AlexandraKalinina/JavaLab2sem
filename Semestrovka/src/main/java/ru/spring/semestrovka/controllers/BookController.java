package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.helpers.ReaderHelper;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private ReaderHelper readerHelper;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ModelAndView book() throws FileNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        readerHelper.loaderFile("books/Letters.txt");
        List<Book> books = readerHelper.getListBook();
        List<Author> authors = readerHelper.getListAuthor();
        List<Genre> genres = readerHelper.getListGenre();
        modelAndView.addObject("books", books);
        modelAndView.addObject("authors", authors);
        modelAndView.addObject("genres", genres);
        modelAndView.setViewName("book");
        return modelAndView;
    }


}
