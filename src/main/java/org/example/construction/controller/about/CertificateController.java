package org.example.construction.controller.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.CertificateDto;
import org.example.construction.service.about.CertificateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.example.construction.model.about.Certificate;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public Certificate create(@RequestPart CertificateDto certificateDto, @RequestPart MultipartFile file) throws IOException {
        return certificateService.save(certificateDto,file);
    }

    @GetMapping
    public List<Certificate> getAll() {
        return certificateService.getAll();
    }

    @GetMapping("/{id}")
    public Certificate getById(@PathVariable Long id) {
        return certificateService.getById(id);
    }

    @PutMapping("/{id}")
    public Certificate update(@PathVariable Long id, @RequestBody CertificateDto certificateDto, @RequestPart MultipartFile file) throws IOException {
        return certificateService.update(id, certificateDto,file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        certificateService.delete(id);
    }
}
