package ru.spring.semestrovka.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;
import ru.spring.semestrovka.repositories.BookRepositories;
import ru.spring.semestrovka.service.AuthorService;
import ru.spring.semestrovka.service.BookService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Reader {

    @Autowired
    private Reader instance;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    List<Book> books = new ArrayList<>();
    List<Author> authors = new ArrayList<>();
    List<Genre> genres = new ArrayList<>();

    public Reader() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream q = classLoader.getResourceAsStream("Letters.txt");
        Scanner scan = new Scanner(q);
        while (scan.hasNextLine()) {
            String[] tmp = scan.nextLine().split("\\$+$");
            tmp[6] = "Letters.txt";
            Optional<Book> book = bookService.readFile(tmp[0], tmp[6]);
            books.add(
                    new Book(
                            book.get().getId(), book.get().getName(), tmp[5]
                    )
            );
            Optional<Author> author = authorService.readFile(tmp[1],tmp[2],tmp[3], book.get().getId());
            authors.add(author.get());

        }
    }
}
