package ru.itis.hateoas.sem.libraryhateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoas.sem.libraryhateoas.service.BookService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books/{book-id}/download", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> download(@PathVariable("book-id") Long bookId) {
        return ResponseEntity.ok(
                EntityModel.of(bookService.download(bookId)));
    }

}
