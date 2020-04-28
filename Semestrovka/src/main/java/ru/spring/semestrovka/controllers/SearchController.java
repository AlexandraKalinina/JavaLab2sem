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

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("query") String name, Model model) {
        List<Book> books = bookService.getListBookByName(name);
        if (books.size() == 0) {
            books.add(new Book("No result.."));
        }
        model.addAttribute("theseBooks", books);
        return "listBooks";
    }


}
