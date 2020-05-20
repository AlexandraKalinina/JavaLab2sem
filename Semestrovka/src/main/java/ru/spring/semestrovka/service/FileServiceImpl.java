package ru.spring.semestrovka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.semestrovka.dto.InformationDto;
import ru.spring.semestrovka.model.Book;
import ru.spring.semestrovka.model.Genre;
import ru.spring.semestrovka.model.User;
import ru.spring.semestrovka.repositories.BookRepositories;
import ru.spring.semestrovka.repositories.UserRepositories;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService{

    private final static String BOOKS_PATH = "C:\\Users\\Александра\\IdeaProjects\\SpringSemestrovka\\src\\main\\resources\\books";

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private UserService userService;

    @Autowired
    BookRepositories bookRepositories;

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public void init() {
        //сохраняем все файлы из папки books в бд
        Optional<User> admin = userRepositories.find(1L);
        //все эти файлы загрузил админ
        try (Stream<Path> filesPaths = Files.walk(Paths.get(BOOKS_PATH))) {

            filesPaths.filter(filePath -> filePath.toFile().isFile()).forEach(
                    filePath -> {
                        File file = filePath.toFile();
                        Book book = null;
                        try {
                            book = Book.builder()
                                    .owner(admin.get())
                                    .text(filePath.toString())
                                    .size(file.length())
                                    .type(Files.probeContentType(filePath))
                                    .name(file.getName().substring(0, file.getName().lastIndexOf(".")))
                                    .build();
                        } catch (IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                        bookRepositories.save(book);
                    }
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public InformationDto getInformation(Long userId) {
        return userService.getInformationByUser(userId);
    }

}
