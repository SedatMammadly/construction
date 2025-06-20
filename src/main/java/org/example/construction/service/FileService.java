package org.example.construction.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {
    private final Path storageDirectory;

    public FileService() throws IOException {
        this.storageDirectory = Paths.get("uploads");
        if (!Files.exists(storageDirectory)) {
            Files.createDirectories(storageDirectory);
        }
    }
    private final Path root = Paths.get("uploads");

     public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Boş fayl  yüklənə bilməz.");
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IOException("Fayl uzantısı tapılmadı.");
        }

        fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueName = timestamp + "_" + UUID.randomUUID() + fileExtension;

        Path destination = this.storageDirectory.resolve(uniqueName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueName;
    }

    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> savedFileNames = new ArrayList<>();
        if (files == null || files.isEmpty()) {
            return savedFileNames;
        }

        for (MultipartFile file : files) {
            savedFileNames.add(uploadFile(file));
        }
        return savedFileNames;
    }
    public Resource getFile(String storedName) throws MalformedURLException {
        Path filePath = root.resolve(storedName);
        return new UrlResource(filePath.toUri());
    }
//salam
    public boolean removeFile(String fileName) {
        try {
            Path filePath = this.storageDirectory.resolve(fileName);
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Boolean> deleteFiles(List<String> fileNames) {
        List<Boolean> deletionResults = new ArrayList<>();
        for (String fileName : fileNames) {
            deletionResults.add(removeFile(fileName));
        }
        return deletionResults;
    }



}