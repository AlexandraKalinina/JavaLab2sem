package ru.itis.mongodb.java.mongodbjava.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    /*@Query(value = "{state: UnBlocked, $or: [{name: ?1}, {size: {$lt: 4}}]}")
    List<Book> find(@Param(""))*/
}
