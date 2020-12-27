package ru.itis.javalab.mongodb.querydsl.querydsl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "authors")
public class Author implements Serializable {
    @Id
    private String _id;
    private String name;
    private String surname;
    private String patronymic;
}
