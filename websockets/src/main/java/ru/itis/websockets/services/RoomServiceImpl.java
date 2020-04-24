package ru.itis.websockets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.websockets.model.Message;
import ru.itis.websockets.model.Room;
import ru.itis.websockets.repositories.RoomRepositories;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepositories roomRepositories;

    private static final Map<Long, List<Long>> listUserAndRoom = new HashMap<>();

    @Override
    public Room getRoom(Long number) {
        Optional<Room> room = roomRepositories.find(number);
        if (!room.isPresent()) {
            Room room1 = Room.builder()
                    .id(number)
                    .build();
            roomRepositories.save(room1);
            return room1;
        } else return room.get();
    }

    @Override
    public void addUserToRoom(Long user, Long number) {
        if (!listUserAndRoom.containsKey(number)) {
            listUserAndRoom.put(number, new ArrayList<>());
        }
        listUserAndRoom.get(number).add(user);
    }

    @Override
    public List<Long> getUsersByRoom(Long number) {
        if (listUserAndRoom.containsKey(number)) {
            return listUserAndRoom.get(number);
        } else return new ArrayList<>();
    }
}
