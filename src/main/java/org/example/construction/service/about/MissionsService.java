package org.example.construction.service.about;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.MissionsDto;
import org.example.construction.model.about.Missions;
import org.example.construction.repository.about.MissionsRepository;
import org.example.construction.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionsService {

    private final MissionsRepository repository;
    private final FileService fileService;

    public Missions save(MissionsDto dto, MultipartFile file) throws IOException {
        Missions missions = new Missions();
        missions.setIcon(fileService.uploadFile(file));
        missions.setTitle(dto.getTitle());
        missions.setDescription(dto.getDescription());
        return repository.save(missions);
    }

    public Missions update(Long id, MissionsDto dto, MultipartFile file) throws IOException {
        Missions missions = repository.findById(id).orElseThrow(() -> new RuntimeException("Mission not found"));
         if (file != null && !file.isEmpty()) {
            missions.setIcon(fileService.uploadFile(file));
        }
        missions.setTitle(dto.getTitle());
        missions.setDescription(dto.getDescription());
        return repository.save(missions);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Missions getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Mission not found"));
    }

    public List<Missions> getAll() {
        return repository.findAll();
    }
}
