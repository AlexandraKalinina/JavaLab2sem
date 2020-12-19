package ru.itis.mongodb.java.mongodbjava.spring;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);

        //find
        List<Book> books = template.find(new Query(
                where("size").lt(4)
                    .orOperator(where("name").is("Asya"),
                            where("state").is("UnBlocked"))), Book.class, "books");
        System.out.println("books");

        //delete
        template.findAllAndRemove(new Query(
                where("_id").is(new ObjectId("5fdd11c7c180b44ff0c7df21"))),
                Book.class, "books");

        //save
        Book book = new Book("RedBook", 1, "UnBlocked");
        template.save(book, "books");

        //update
        Update update = new Update();
        update.set("name", "BlueBook");
        template.updateFirst(new Query(
                where("_id").is(new ObjectId("5fddc23123840a7570d4cb1c"))),
                update, Book.class, "books");
    }

}
