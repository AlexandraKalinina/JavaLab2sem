package ru.itis.javalab.mongodb.querydsl.querydsl.controllers;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.mongodb.querydsl.querydsl.models.Book;
import ru.itis.javalab.mongodb.querydsl.querydsl.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchByPredicate(@QuerydslPredicate(root = Book.class, bindings = BookRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(bookRepository.findAll(predicate).spliterator(), true)
                .collect(Collectors.toList())
        );
    }

}
