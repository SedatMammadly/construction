package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.setem.SetemDto;
import org.example.construction.model.setem.Setem;
import org.example.construction.repository.setem.SetemRepository;
import org.example.construction.service.setem.SetemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/setem")
@RequiredArgsConstructor
public class SetemController {
    private final SetemRepository setemRepository;
    private final SetemService setemService;

    @GetMapping
    public List<Setem> getAllSetems() {
        return setemService.getAll();
    }

    @GetMapping("/{id}")
    public Setem getSetemById(@PathVariable Long id) {
        return setemService.getById(id);
    }

    @PostMapping
    public Setem createSetem(@RequestPart SetemDto dto,
                             @RequestPart MultipartFile icon,
                             @RequestPart(required = false) List<MultipartFile> images) throws IOException {
        return setemService.create(dto, icon, images);
    }

    @PutMapping("/{id}")
    public Setem updateSetem(@PathVariable Long id,
                             @RequestPart SetemDto dto,
                             @RequestPart MultipartFile icon,
                             @RequestPart(required = false) List<MultipartFile> images) throws IOException {
        return setemService.update(id, dto, icon, images);
    }

    @DeleteMapping("/{id}")
    public void deleteSetem(@PathVariable Long id) {
        setemService.delete(id);
    }

}
