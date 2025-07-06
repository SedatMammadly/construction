package org.example.construction.service.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ManageTeamDto;
import org.example.construction.model.about.ManageTeam;
import org.example.construction.repository.about.ManageTeamRepository;
import org.example.construction.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageTeamService {

    private final ManageTeamRepository repository;
    private final FileService fileService;

    public ManageTeam save(ManageTeamDto dto, MultipartFile file) throws IOException {
        ManageTeam team = new ManageTeam();
        team.setTitle(dto.getTitle());
        team.setWork(dto.getWork());
        team.setParagraph(dto.getParagraph());
        team.setImage(fileService.uploadFile(file));
        return repository.save(team);
    }

    public ManageTeam update(Long id, ManageTeamDto dto, MultipartFile file) throws IOException {
        ManageTeam existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found with id: " + id));
         existing.setWork(dto.getWork());
        existing.setTitle(dto.getTitle());
        existing.setParagraph(dto.getParagraph());
        if (file != null && !file.isEmpty()) {
            existing.setImage(fileService.uploadFile(file));
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ManageTeam getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found with id: " + id));
    }

    public List<ManageTeam> getAll() {
        return repository.findAll();
    }
}

