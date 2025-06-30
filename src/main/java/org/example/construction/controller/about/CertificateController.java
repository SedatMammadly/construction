package org.example.construction.controller.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.CertificateDto;
import org.example.construction.model.about.Certificate;
import org.example.construction.service.about.CertificateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    // CREATE
    @PostMapping("/add")
    public Certificate addCertificate(@RequestPart CertificateDto certificateDto,
                                      @RequestPart MultipartFile file) throws IOException {
        return certificateService.save(certificateDto, file);
    }

    // READ ALL
    @GetMapping("/getAll")
    public List<Certificate> getAllCertificates() {
        return certificateService.getAll();
    }

    // READ BY ID
    @GetMapping("/get/{id}")
    public Certificate getCertificateById(@PathVariable Long id) {
        return certificateService.getById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public Certificate updateCertificate(@PathVariable Long id,
                                         @RequestPart CertificateDto certificateDto,
                                         @RequestPart MultipartFile file) throws IOException {
        return certificateService.update(id, certificateDto, file);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public void deleteCertificate(@PathVariable Long id) {
        certificateService.delete(id);
    }
}
