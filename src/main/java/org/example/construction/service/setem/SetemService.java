package org.example.construction.service.setem;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.SetemUpdateDto;
import org.example.construction.service.FileService;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.setem.SetemDto;
import org.example.construction.model.setem.Setem;
import org.example.construction.repository.setem.SetemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SetemService {

    private final SetemRepository setemRepository;
    private final FileService fileService;

    public List<Setem> getAll() {
        return setemRepository.findAll();
    }

    public Setem getById(Long id) {
        return setemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setem not found"));
    }

    public Setem create(SetemDto dto, MultipartFile icon, List<MultipartFile> images) throws IOException {
        Setem setem = new Setem();
        setem.setHeader(dto.getHeader());
        setem.setDescription(dto.getDescription());
        setem.setContent(dto.getContent());


        setem.setIcon(fileService.uploadFile(icon));
        setem.setImages(fileService.uploadFiles(images));


        return setemRepository.save(setem);
    }

    public void delete(Long id) {
        setemRepository.deleteById(id);
    }

    public Setem update(Long id, SetemUpdateDto dto, MultipartFile icon, List<MultipartFile> images) throws IOException {
        Setem setem = setemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setem not found"));

        setem.setHeader(dto.getHeader());
        setem.setDescription(dto.getDescription());
        setem.setContent(dto.getContent());

        if (icon != null && !icon.isEmpty()) {
            fileService.deleteFile(setem.getIcon());
            setem.setIcon(fileService.uploadFile(icon));
        }

        List<String> toRemove = new ArrayList<>();

        for (String image : setem.getImages()) {
            if (dto.getImages().contains(image)) {
                toRemove.add(image);
            }
        }

        fileService.deleteFiles(toRemove);
        setem.setImages(dto.getImages());

        if (images != null && !images.isEmpty()) {
            setem.getImages().addAll(fileService.uploadFiles(images));
        }


        return setemRepository.save(setem);
    }

}
