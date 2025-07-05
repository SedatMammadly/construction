package org.example.construction.controller.special;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.SpecialDto;
import org.example.construction.dto.SpecialUpdateDto;
import org.example.construction.model.Special;
import org.example.construction.repository.SpecialRepository;
import org.example.construction.service.SpecialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/specials")
@RequiredArgsConstructor
public class SpecialController {

    private final SpecialService specialService;
    private final SpecialRepository specialRepository;

    @PostMapping("/add")
    public ResponseEntity<Special> createSpecial(
            @RequestPart("specialDto") SpecialDto specialDto,
            @RequestPart("images") List<MultipartFile> images
    ) throws IOException {
        Special created = specialService.createSpecial(specialDto, images);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Special>> getAll() {
        return ResponseEntity.ok(specialService.getAllSpecials());
    }

    @GetMapping("/getBySlug/{slug}")
    public ResponseEntity<Special> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(specialRepository.findBySlug(slug));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Special> getById(@PathVariable Long id) {
        return specialService.getSpecialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Special> update(
            @PathVariable Long id,
            @RequestPart("request") SpecialUpdateDto specialDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {
        Special updated = specialService.updateSpecial(id, specialDto, images);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        specialService.deleteSpecial(id);
        return ResponseEntity.noContent().build();
    }
}

