package ru.spring.stompproject.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Service
public class FileServiceImpl implements FileService {

    @Value("${storage.path}")
    private String dir;

    @Override
    public String uploadFile(MultipartFile file, Long id) {
        String nameFile ="";
        try {
            nameFile = file.getOriginalFilename();
            String[] data = nameFile.split("\\.");
            File dirFile = new File(dir + File.separator);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File loadFile = new File(dirFile.getAbsolutePath() + File.separator + id + "." + data[1]);
            file.transferTo(loadFile);
            return loadFile.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public File downloadFile(String path) {
        String name = dir + "\\" + path;
        return new File(name);
    }
}
