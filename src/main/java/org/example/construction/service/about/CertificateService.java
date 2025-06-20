package org.example.construction.service.about;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.CertificateDto;
import org.example.construction.model.about.Certificate;
import org.example.construction.repository.about.CertificateRepository;
import org.example.construction.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository repository;
    private final FileService fileService;

     public Certificate save(CertificateDto certificateDto, MultipartFile file) throws IOException {
         Certificate certificate=new Certificate();
         certificate.setName(certificateDto.getName());
         certificate.setImage(fileService.uploadFile(file));
        return repository.save(certificate);
    }

     public Certificate update(Long id, CertificateDto certificateDto,MultipartFile file) throws IOException {
        Certificate existing = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Certificate not found with id: " + id));
        fileService.removeFile(existing.getImage());
        existing.setName(certificateDto.getName());
        existing.setImage(fileService.uploadFile(file));
        return repository.save(existing);
    }

     public void delete(Long id) {
        repository.deleteById(id);
    }

     public Certificate getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Certificate not found with id: " + id));
    }

     public List<Certificate> getAll() {
        return repository.findAll();
    }
}
