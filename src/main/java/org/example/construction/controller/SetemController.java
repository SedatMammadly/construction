package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.SetemUpdateDto;
import org.example.construction.dto.setem.SetemDto;
import org.example.construction.model.setem.Setem;
import org.example.construction.service.setem.SetemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/setem")
@RequiredArgsConstructor
public class SetemController {
    private final SetemService setemService;

    @GetMapping("/getAll")
    public List<Setem> getAllSetems() {
        return setemService.getAll();
    }

    @GetMapping("/get/{id}")
    public Setem getSetemById(@PathVariable Long id) {
        return setemService.getById(id);
    }

    @PostMapping("/add")
    public Setem addSetem(
            @RequestPart SetemDto dto,
            @RequestPart MultipartFile icon
    ) throws IOException {
        return setemService.create(dto, icon );
    }

    @PutMapping("/update/{id}")
    public Setem updateSetem(
            @PathVariable Long id,
            @RequestPart SetemUpdateDto setemUpdateDto,
            @RequestPart(required = false) MultipartFile icon
    ) throws IOException {
        return setemService.update(id, setemUpdateDto, icon);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSetem(@PathVariable Long id) {
        setemService.delete(id);
    }
}
