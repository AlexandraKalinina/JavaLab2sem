package ru.spring.semestrovka.helpers;


import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReaderHelper {
    Book loaderFile(Long id) throws FileNotFoundException;
}
