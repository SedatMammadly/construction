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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {
    public Path fileStorageLocation;

    public FileService(FileConfig fileConfig) {
        this.fileStorageLocation = fileConfig.getFileStorageLocation();
    }

    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = uploadFile(file);
            urls.add(url);
        }
        return urls;
    }


    public String uploadFile(MultipartFile file) {
        String url;
        try {
            Path target = fileStorageLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()).trim().replaceAll("\\s+", "_"));
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            url = "http://localhost:8080/images/" + file.getOriginalFilename().trim().replaceAll("\\s+", "_");
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename(), e);
        }
        return url;
    }

    public void removeFile(String url) {
        String fileName = extractFileName(url);
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
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
