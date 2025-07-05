package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.KsmDto;
import org.example.construction.dto.KsmUpdateDto;
import org.example.construction.model.Ksm;
import org.example.construction.repository.KsmRepository;
import org.example.construction.service.KsmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/ksm")
@RequiredArgsConstructor
public class KsmController {
    private final KsmService ksmService;
    private final KsmRepository ksmRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Ksm>> getAllKsms() {
        return ResponseEntity.ok(ksmRepository.findAll());
    }

    @GetMapping("/getBySlug/{slug}")
    public ResponseEntity<Ksm> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(ksmRepository.findBySlug(slug));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Ksm>> getKsmById(@PathVariable Integer id) {
        return ResponseEntity.ok(ksmRepository.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Ksm> addKsmCards(
            @RequestPart(name = "request") KsmDto ksmDto,
            @RequestPart MultipartFile icon,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        Ksm ksm = ksmService.addKsmCards(ksmDto, images, icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ksm> updateKsmCard(
            @PathVariable int id,
            @RequestPart(name = "request") KsmUpdateDto ksmUpdateDto,
            @RequestPart(required = false) MultipartFile icon,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        Ksm ksm = ksmService.updateKsmCard(id, ksmUpdateDto, icon, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteKsmCard(@PathVariable int id) {
        ksmService.deleteKsmCard(id);
        return ResponseEntity.noContent().build();
    }
}
