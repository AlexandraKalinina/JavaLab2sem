package ru.itis.websockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.websockets.dto.MessageDto;
import ru.itis.websockets.model.Message;
import ru.itis.websockets.model.Room;
import ru.itis.websockets.model.User;
import ru.itis.websockets.repositories.MessageRepositories;
import ru.itis.websockets.repositories.RoomRepositories;
import ru.itis.websockets.repositories.UserRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepositories messageRepositories;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private RoomRepositories roomRepositories;

    @Override
    public void saveMessage(MessageDto messageDto) {
        Optional<User> user = userRepositories.find(messageDto.getUserId());
        Optional<Room> room = roomRepositories.find(messageDto.getRoomId());
        Message message = Message.builder()
                .text(messageDto.getText())
                .user(user.get())
                .room(room.get())
                .build();
        if (room.isPresent() & user.isPresent()) {
            messageRepositories.save(message);
        }
    }

    @Override
    public List<Message> getListMessageForRoom(Long id_room) {
        Optional<Room> room = roomRepositories.find(id_room);
        List<Message> messages = messageRepositories.getListByIdRoom(room.get());
        return messages;
    }
}
