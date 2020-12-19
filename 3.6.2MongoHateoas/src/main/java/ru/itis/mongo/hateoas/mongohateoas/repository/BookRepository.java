package ru.itis.mongo.hateoas.mongohateoas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.mongo.hateoas.mongohateoas.model.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    @RestResource(path = "unBlocked", rel = "unBlocked")
    @Query(value = "{state: UnBlocked, $or: [{name: ?1}, {size: {$lt: 4}}]}")
    List<Book> find(@Param("name") String name, @Param("size") Integer size);

}
