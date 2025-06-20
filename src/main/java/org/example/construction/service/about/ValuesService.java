package org.example.construction.service.about;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ValuesDto;
import org.example.construction.model.about.Values;
import org.example.construction.repository.about.ValuesRepository;
import org.example.construction.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValuesService {

    private final ValuesRepository repository;
    private final FileService fileService;

    public Values save(ValuesDto dto, MultipartFile file) throws IOException {
        Values value = new Values();
        value.setIcon(fileService.uploadFile(file));
        value.setTitle(dto.getTitle());
        value.setParagraph(dto.getParagraph());
        return repository.save(value);
    }

    public Values update(Long id, ValuesDto dto, MultipartFile file) throws IOException {
        Values value = repository.findById(id).orElseThrow(() -> new RuntimeException("Value not found"));
        value.setIcon(fileService.uploadFile(file));
        value.setTitle(dto.getTitle());
        value.setParagraph(dto.getParagraph());
        return repository.save(value);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Values getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Value not found"));
    }

    public List<Values> getAll() {
        return repository.findAll();
    }
}
