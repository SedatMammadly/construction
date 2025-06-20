package org.example.construction.service.about;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ManagementStructureDto;
import org.example.construction.model.about.ManagementStructure;
import org.example.construction.repository.about.ManagementStructureRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementStructureService {

    private final ManagementStructureRepository repository;

    public ManagementStructure save(ManagementStructureDto dto) {
        repository.deleteAll();
        ManagementStructure ms = new ManagementStructure();
        ms.setParagraph(dto.getParagraph());
        return repository.save(ms);
    }

    public ManagementStructure get() {
        return repository.findAll().stream().findFirst().orElse(null);
    }
}
