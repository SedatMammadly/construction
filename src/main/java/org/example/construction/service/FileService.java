package org.example.construction.service;

import org.example.construction.config.FileConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileService {
    public Path imageStorageLocation;
    public Path fileStorageLocation;

    public FileService(FileConfig fileConfig) {
        this.imageStorageLocation = fileConfig.getImgFileStorageLocation();
        this.fileStorageLocation = fileConfig.getFileStorageLocation();
    }


    public String uploadFile(MultipartFile file, String type) {
        String url = null;
        try {
            if (type.equals("image")) {
                Path target = imageStorageLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()).trim().replaceAll("\\s+", "_"));
                Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
                url = switchToUrl(file.getOriginalFilename(), type);
            } else if (type.equals("file")) {
                Path target = fileStorageLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()).trim().replaceAll("\\s+", "_"));
                Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
                url = switchToUrl(file.getOriginalFilename(), type);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename(), e);
        }
        return url;
    }

    public String switchToUrl(String originalFilename, String type) {
        if (type.equals("image")) {
            return "http://localhost:8080/images/" + originalFilename.trim().replaceAll("\\s+", "_");
        }
        return "http://localhost:8080/files/" + originalFilename.trim().replaceAll("\\s+", "_");
    }

    public void removeFile(String url) {
        String fileName = extractFileName(url);
        Path filePath = imageStorageLocation.resolve(fileName).normalize();
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file " + fileName, e);
        }
    }


    public String extractFileName(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();
            return Paths.get(path).getFileName().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid URL: " + url);
        }
    }
}
