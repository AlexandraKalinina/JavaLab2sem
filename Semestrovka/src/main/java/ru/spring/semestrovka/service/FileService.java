package ru.spring.semestrovka.service;

import ru.spring.semestrovka.dto.InformationDto;
import ru.spring.semestrovka.model.Book;

import java.io.IOException;

public interface FileService {
    void init();

    InformationDto getInformation(Long userId);
}
