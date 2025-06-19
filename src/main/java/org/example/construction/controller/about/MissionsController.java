package org.example.construction.controller.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.MissionsDto;
import org.example.construction.model.about.Missions;
import org.example.construction.service.about.MissionsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionsController {

    private final MissionsService service;

    @PostMapping
    public Missions create(@RequestPart MissionsDto dto, @RequestPart MultipartFile file) throws IOException {
        return service.save(dto,file);
    }

    @GetMapping
    public List<Missions> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Missions getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Missions update(@PathVariable Long id, @RequestPart MissionsDto dto, @RequestPart MultipartFile file) throws IOException {
        return service.update(id, dto,file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
