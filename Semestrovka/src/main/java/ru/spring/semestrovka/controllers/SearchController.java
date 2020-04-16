package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.semestrovka.service.MessageService;

@Controller
public class SearchController {

    @Autowired
    private MessageService messageService;

   /* @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<Object> search(@RequestParam("query") String name) {

    }*/

}
