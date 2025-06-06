package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.KsmCardDto;
import org.example.construction.model.Ksm;
import org.example.construction.service.KsmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ksm")
@RequiredArgsConstructor
public class KsmController {
    private final KsmService ksmService;


    @GetMapping
    public ResponseEntity<Ksm> getKsmPage() {
        return ResponseEntity.ok(ksmService.getKsmPage());
    }

    @PostMapping
    public ResponseEntity<Ksm> addKsmCards(@RequestPart(name = "request") List<KsmCardDto> KsmCardDtos,
                                           @RequestPart(required = false) List<MultipartFile> icons) {
        Ksm ksm = ksmService.addKsmCards(KsmCardDtos, icons);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksm);
    }

    @PutMapping("/update/ksmCard/{index}")
    public ResponseEntity<Ksm> updateKsmCard(@PathVariable int index, @RequestPart(name = "request") KsmCardDto ksmCardDto,
                                             @RequestPart(required = false) MultipartFile icon,
                                             @RequestPart(required = false) List<MultipartFile> images) {
        Ksm ksm = ksmService.updateKsmCard(index, ksmCardDto, icon, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(ksm);
    }

    @DeleteMapping("/delete/ksmCard/{index}")
    public ResponseEntity<Void> deleteKsmCard(@PathVariable int index) {
        ksmService.deleteKsmCard(index);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
