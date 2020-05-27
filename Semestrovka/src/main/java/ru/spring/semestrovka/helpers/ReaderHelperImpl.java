package ru.spring.semestrovka.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spring.semestrovka.model.Author;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;
import ru.spring.semestrovka.service.AuthorService;
import ru.spring.semestrovka.service.BookService;
import ru.spring.semestrovka.service.GenreService;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

@Component
public class ReaderHelperImpl implements ReaderHelper {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Override
    public Book loaderFile(Long id) throws FileNotFoundException {
        Optional<Book> book = bookService.find(id);
        ClassLoader classLoader = getClass().getClassLoader();
        String[] paths = book.get().getText().split("\\\\");
        String p = paths[8] + "/" + paths[9];
        InputStream q = classLoader.getResourceAsStream(p);
        Scanner scan = new Scanner(q, "UTF-8");
        List<String> data = new ArrayList<>();
        String text = "";
        while (scan.hasNextLine()) {
            String[] tmp = scan.nextLine().split("\\+\\+\\+");
            for (int j = 0; j < tmp.length; j ++) {
                if (data.size()>4) {
                    text += tmp[j];
                } else data.add(tmp[j]);
            }
        }
        data.add(text);
        genreService.readFile(data.get(4), book.get());
        authorService.readFile(data.get(1),data.get(2),data.get(3), book.get());
        Book newBook = new Book(book.get().getId(), book.get().getName(), data.get(5), book.get().getAuthors(), book.get().getGenres());
        return newBook;
    }

}
