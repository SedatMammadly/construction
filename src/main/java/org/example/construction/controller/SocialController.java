package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.SocialDto;
import org.example.construction.model.Social;
import org.example.construction.service.SocialService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/social")
@RequiredArgsConstructor
public class SocialController {

    private final SocialService service;

    @PostMapping("/add")
    public Social add(
            @RequestPart SocialDto dto,
            @RequestPart(required = false) MultipartFile image
    ) throws IOException {
        return service.create(dto, image);
    }

    @GetMapping("/getAll")
    public List<Social> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Social getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Social update(
            @PathVariable Integer id,
            @RequestPart SocialDto dto,
            @RequestPart(required = false) MultipartFile image
    ) throws IOException {
        return service.update(id, dto, image);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

