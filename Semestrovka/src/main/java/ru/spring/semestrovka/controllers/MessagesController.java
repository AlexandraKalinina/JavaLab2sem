package ru.spring.semestrovka.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.semestrovka.dto.MessageDto;
import ru.spring.semestrovka.service.MessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessagesController {

    private static final Map<Long, List<MessageDto>> messages = new HashMap<>();

    @Autowired
    private MessageService messageService;

    // получили сообщение от какой либо страницы - мы его разошлем во все другие страницы
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        //сохранеяем сообщение в бд
        messageService.saveMessage(message);
        // если сообщений с этой или для этой страницы еще не было
        if (!messages.containsKey(message.getUserId())) {
            // добавляем эту страницу в Map-у страниц
            messages.put(message.getUserId(), new ArrayList<>());
        }
        // полученное сообщение добавляем для всех открытых страниц нашего приложения
        for (List<MessageDto> pageMessages : messages.values()) {
            // перед тем как положить сообщение для какой-либо страницы
            // мы список сообщений блокируем
            synchronized (pageMessages) {
                // добавляем сообщение
                pageMessages.add(message);
                // говорим, что другие потоки могут воспользоваться этим списком
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }

    // получить все сообщения для текущего запроса
    @SneakyThrows
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("userId") Long userId) {
        // получили список сообшений для страницы и заблокировали его
        synchronized (messages.get(userId)) {
            // если нет сообщений уходим в ожидание
            if (messages.get(userId).isEmpty()) {
                messages.get(userId).wait();
            }
        }
        // если сообщения есть - то кладем их в новых список
        List<MessageDto> response = new ArrayList<>(messages.get(userId));
        // удаляем сообщения из мапы
        messages.get(userId).clear();
        return ResponseEntity.ok(response);
    }
}
