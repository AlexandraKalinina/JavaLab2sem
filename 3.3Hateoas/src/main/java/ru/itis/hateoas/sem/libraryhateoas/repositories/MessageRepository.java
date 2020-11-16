package ru.itis.hateoas.sem.libraryhateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.sem.libraryhateoas.model.Message;
import sun.misc.resources.Messages_zh_HK;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
