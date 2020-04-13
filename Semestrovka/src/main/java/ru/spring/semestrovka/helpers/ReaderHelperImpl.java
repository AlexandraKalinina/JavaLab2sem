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

    List<Book> books = new ArrayList<>();
    List<Author> authors = new ArrayList<>();
    List<Genre> genres = new ArrayList<>();

    @Override
    public void loaderFile(String path) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream q = classLoader.getResourceAsStream(path);
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
        Optional<Book> book = bookService.readFile(data.get(0), path);
        //to do: сделать чтобы возвращался лист авторов, а не один автор и кидать в книгу,
        // а одну книгу в контроллер, без листов с авторами и тд
        Book newBook = new Book(book.get().getName(), data.get(5));
        books.add(newBook);
        Optional<Author> author = authorService.readFile(data.get(1),data.get(2),data.get(3), book.get());
        authors.add(author.get());
        Optional<Genre> genre = genreService.readFile(data.get(4), book.get());
        genres.add(genre.get());
    }

    public List<Book> getListBook() {
        return new ArrayList<>(books);
    }

    public List<Author> getListAuthor() {
        return new ArrayList<>(authors);
    }

    public List<Genre> getListGenre() {
        return new ArrayList<>(genres);
    }
}
