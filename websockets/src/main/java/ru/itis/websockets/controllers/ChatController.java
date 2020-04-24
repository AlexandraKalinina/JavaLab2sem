package ru.itis.websockets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.websockets.dto.JoinDto;
import ru.itis.websockets.dto.UserDto;
import ru.itis.websockets.model.Message;
import ru.itis.websockets.model.Room;
import ru.itis.websockets.services.CookieService;
import ru.itis.websockets.services.MessageService;
import ru.itis.websockets.services.RoomService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private CookieService cookieService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/chat")
    public String getChatPage(Model model, ServletRequest servletRequest, @RequestParam Long number) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        UserDto userDto = cookieService.getToken(request);
        if (userDto != null) {
            if (number!=null) {
                Room room = roomService.getRoom(number);
                roomService.addUserToRoom(userDto.getId(), room.getId());
                List<Message> messages = messageService.getListMessageForRoom(room.getId());
                if (messages == null) {
                    messages = new ArrayList<>();
                }
                model.addAttribute("userId", userDto.getId());
                model.addAttribute("number", room.getId());
                model.addAttribute("messages", messages);
                return "chat";
            } else return "room";
        } else return "signIn";
    }

}
