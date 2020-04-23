package ru.itis.websockets.services;

import ru.itis.websockets.dto.MessageDto;
import ru.itis.websockets.model.Message;

import java.util.List;

public interface MessageService {
    void saveMessage(MessageDto messageDto);
    List<Message> getListMessageForRoom(Long id_room);
}
