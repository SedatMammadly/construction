package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.KsmCardDto;
import org.example.construction.model.Ksm;
import org.example.construction.repository.KsmRepository;
import org.example.construction.service.KsmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ksm")
@RequiredArgsConstructor
public class
KsmController {
    private final KsmService ksmService;
private final KsmRepository ksmRepository;

    @GetMapping
    public ResponseEntity<List<Ksm>> getKsmPage() {
        return ResponseEntity.ok(ksmRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Ksm> addKsmCards(@RequestPart(name = "request") KsmCardDto ksmCardDto,
                                           @RequestPart() MultipartFile icon,
                                           @RequestPart(required = false) List<MultipartFile> images) throws IOException {
        Ksm ksm = ksmService.addKsmCards(ksmCardDto,images,icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksm);
    }

    @PutMapping("/update/ksmCard/{id}")
    public ResponseEntity<Ksm> updateKsmCard(@PathVariable int id, @RequestPart(name = "request") KsmCardDto ksmCardDto,
                                             @RequestPart(required = false) MultipartFile icon,
                                             @RequestPart(required = false) List<MultipartFile> images) throws IOException {
        Ksm ksm = ksmService.updateKsmCard(id, ksmCardDto, icon, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksm);
    }

    @DeleteMapping("/delete/ksmCard/{id}")
    public ResponseEntity<Void> deleteKsmCard(@PathVariable int id) {
        ksmService.deleteKsmCard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
