package ru.itis.websockets.services;

import ru.itis.websockets.model.Room;

import java.util.List;

public interface RoomService {
    Room getRoom(Long number);
    void addUserToRoom(Long user, Long number);
    List<Long> getUsersByRoom(Long number);
}
