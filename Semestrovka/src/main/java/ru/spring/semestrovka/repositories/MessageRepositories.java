package ru.spring.semestrovka.repositories;

import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Message;

import java.util.List;

public interface MessageRepositories extends CrudRepositories<Message, Long> {
    List<Message> getListByIdBook(Book book);
}
