package ru.spring.semestrovka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.spring.semestrovka.model.Message;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/chat/{id-book}", method = RequestMethod.GET)
    public String getChatPage(@PathVariable("id-book") String id, Model model, HttpServletRequest req ) {
        HttpSession session = req.getSession();
        List<Message> messages = messageService.getListMessageForBook(Long.parseLong(id));
        if (session.getAttribute("user") != null) {
            Optional<User> user = (Optional<User>) session.getAttribute("user");
            model.addAttribute("userId", user.get().getId());
            model.addAttribute("bookId", id);
            model.addAttribute("messages", messages);
            model.addAttribute("name", user.get().getName());
            return "chat";
        }
        else return "signInComment";
    }
}
