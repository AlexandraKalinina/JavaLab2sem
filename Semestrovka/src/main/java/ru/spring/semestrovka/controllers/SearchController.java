package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.service.BookService;
import ru.spring.semestrovka.service.MessageService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<String>> search(@RequestParam("query") String name) {
        List<Book> books = bookService.getListBookByName(name);
        List<String> names = new ArrayList<>();
        for (Book b: books) {
            names.add(b.getName());
        }
        return ResponseEntity.ok(names);

    }
    @RequestMapping(value = "/searchBooks", method = RequestMethod.GET)
    public ModelAndView search() {
        return new ModelAndView("listBooks");
    }

}
