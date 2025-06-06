package org.example.construction.init;

import org.example.construction.config.FileConfig;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileInitializer {

    public FileInitializer(FileConfig fileConfig) {
        Path imagesPath = fileConfig.getImgFileStorageLocation();
        Path filePath = fileConfig.getFileStorageLocation();
        try{
            Files.createDirectories(filePath);
            Files.createDirectories(imagesPath);
        }
        catch (IOException e){
          throw new IllegalStateException("Could not create storage directory: " + imagesPath, e);
        }
    }
}
