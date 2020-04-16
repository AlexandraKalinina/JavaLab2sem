package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.semestrovka.dto.MessageDto;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Message;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.BookRepositories;
import ru.spring.semestrovka.repositories.MessageRepositories;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepositories messageRepositories;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private BookRepositories bookRepositories;

    @Override
    public void saveMessage(MessageDto messageDto) {
        Optional<Book> book = bookRepositories.find(messageDto.getBookId());
        Optional<User> user = userRepositories.find(messageDto.getUserId());
        Message message = Message.builder()
                .text(messageDto.getText())
                .book(book.get())
                .user(user.get())
                .build();
        if (book.isPresent() & user.isPresent()) {
            messageRepositories.save(message);
        }
    }

    @Override
    public List<Message> getListMessageForBook(Long id_book) {
        Optional<Book> book = bookRepositories.find(id_book);
        List<Message> messages = messageRepositories.getListByIdBook(book.get());
        List<Message> back = new ArrayList<>();
        int i = messages.size() - 1;
        while (i >= 0) {
            back.add(messages.get(i));
            i--;
        }
        return back;
    }
}
