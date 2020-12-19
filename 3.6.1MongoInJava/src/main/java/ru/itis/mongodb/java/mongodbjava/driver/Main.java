package ru.itis.mongodb.java.mongodbjava.driver;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("library");
        MongoCollection<Document> collection = database.getCollection("books");

        //find elements'
        collection.find()
                .forEach(x -> System.out.println(x.getString("name")));

        //delete elements'
        collection.deleteMany(new Document("name", "Asya"));

        //save
        collection.insertOne(new Document("name", "GreenBook")
                        .append("path", "c:/books/txt/GreenBook")
                        .append("size", 1));

        //update
        collection.updateOne(new Document("_id", new ObjectId("5fdd129941222e0d6f391722")),
                new Document("$set", new Document("state", "Blocking")));


        Document searchQuery = new Document();
        searchQuery
                .append("size",  new Document("$lt", 4))
                .append("$or", Arrays.asList(
                        new Document("name", "Asya"),
                        new Document("state", "UnBlocked")));
        FindIterable<Document> resultDocuments = collection.find(searchQuery);

        FindIterable<Document> documents = collection.find(searchQuery)
                .projection(new Document("name", 1)
                        .append("path", 1)
                        .append("size", 1)
                        .append("_id", 0));

        FindIterable<Document> documents1 = collection.find(
                and(new Document("size",  new Document("$lt", 4)),
                        or(new Document("name", "Asya"), new Document("state", "UnBlocked")))
        ).projection(fields(include("size", "name"), excludeId()));

        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
        for (Document document : documents1) {
            System.out.println(document.toJson());
        }
    }
}
