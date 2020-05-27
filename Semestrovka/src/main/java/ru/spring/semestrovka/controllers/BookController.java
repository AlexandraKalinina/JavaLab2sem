package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.helpers.ReaderHelper;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class BookController {

    @Autowired
    private ReaderHelper readerHelper;

    private Book book;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/sequence", method = RequestMethod.GET)
    public ModelAndView book(@RequestParam("id") Long id) throws FileNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        book = readerHelper.loaderFile(id);
        List<Book> books = new ArrayList<>();
        books.add(book);
        Set<Author> authors = book.getAuthors();
        Set<Genre> genres = book.getGenres();
        modelAndView.addObject("books", books);
        modelAndView.addObject("authors", authors);
        modelAndView.addObject("genres", genres);
        modelAndView.setViewName("book");
        return modelAndView;
    }
    @RequestMapping(value = "/book/chat", method = RequestMethod.GET)
    public ModelAndView comment() {
        if (book != null) {
            return new ModelAndView("redirect:/chat/" + book.getId());
        } else return new ModelAndView("book");
    }


}
