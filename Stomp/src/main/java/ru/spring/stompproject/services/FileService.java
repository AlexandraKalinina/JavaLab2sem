package ru.spring.stompproject.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    String uploadFile(MultipartFile file, Long id);
    File downloadFile(String path);
}
