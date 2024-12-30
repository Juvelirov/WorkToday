package com.example.worktodayproject.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileProcessingUtils {

    public String uploadFile(MultipartFile file, String uploadPath) {
        if (file != null && !file.isEmpty()) {
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                try {
                    Files.createDirectories(uploadDir);
                } catch (IOException e) {
                    throw new RuntimeException("Can not create directory", e);
                }
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            try {
                Path filePath = uploadDir.resolve(resultFileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Can not upload file", e);
            }

            return  uploadPath + "/" + resultFileName;
        }

        return null;
    }
}
