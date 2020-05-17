package ru.spring.semestrovka.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.semestrovka.dto.InformationDto;
import ru.spring.semestrovka.service.FileService;

import java.io.IOException;

@RestController
public class FilesController {
    @Autowired
    private FileService fileService;

    @GetMapping("/books/init")
    public ResponseEntity<?> init() {
        fileService.init();
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/{user-id}/books/information")
    public ResponseEntity<InformationDto> getInformation(@PathVariable("user-id") Long userId) {
        InformationDto result = fileService.getInformation(userId);
        return ResponseEntity.ok(result);
    }




}
