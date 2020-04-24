package ru.itis.websockets.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.websockets.dto.MessageDto;
import ru.itis.websockets.services.MessageService;
import ru.itis.websockets.services.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<Long, WebSocketSession> sessions = new HashMap<Long, WebSocketSession>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RoomService roomService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put((Long) session.getAttributes().get("userId"),session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //System.out.println(message.getPayload());
        String messageText = (String) message.getPayload();
        MessageDto messageFromJson = objectMapper.readValue(messageText, MessageDto.class);
        messageService.saveMessage(messageFromJson);
        if (!sessions.containsKey(messageFromJson.getUserId())) {
            sessions.put(messageFromJson.getUserId(), session);
        }
        List<Long> users = roomService.getUsersByRoom(messageFromJson.getRoomId());
        if (users.size() != 0) {
            for (Long u : users) {
                if (sessions.containsKey(u)) {
                    sessions.get(u).sendMessage(message);
                }
            }
        }
    }

}
