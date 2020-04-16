package ru.spring.semestrovka.service;

import ru.spring.semestrovka.dto.MessageDto;
import ru.spring.semestrovka.model.Message;

import java.util.List;

public interface MessageService {
    void saveMessage(MessageDto messageDto);
    List<Message> getListMessageForBook(Long id_book);
}
