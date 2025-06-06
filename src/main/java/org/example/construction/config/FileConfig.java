package org.example.construction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileConfig {
    @Value("${file.image.dir}")
    private String imageDir;
    @Value("${file.dir}")
    private String fileDir;

    public Path getImgFileStorageLocation() {
        return Paths.get(imageDir).toAbsolutePath().normalize();
    }
    public Path getFileStorageLocation() {
        return Paths.get(fileDir).toAbsolutePath().normalize();
    }



}
