package ru.itis.websockets.repositories;

import ru.itis.websockets.model.Message;
import ru.itis.websockets.model.Room;

import java.util.List;

public interface MessageRepositories extends CrudRepositories<Message, Long> {
    List<Message> getListByIdRoom(Room room);
}
