package ru.itis.mongodb.java.mongodbjava.spring;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private String name;
    private String surname;
    private String patronymic;
}
