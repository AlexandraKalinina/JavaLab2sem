package ru.spring.semestrovka.helpers;


import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReaderHelper {
    void loaderFile(String path) throws FileNotFoundException;
    List<Book> getListBook();
    List<Author> getListAuthor();
    List<Genre> getListGenre();
}
