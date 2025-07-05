package org.example.construction.controller.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ManageTeamDto;
import org.example.construction.model.about.ManageTeam;
import org.example.construction.service.about.ManageTeamService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class ManageTeamController {

    private final ManageTeamService service;

    @PostMapping("/add")
    public ManageTeam add(
            @RequestPart ManageTeamDto dto,
            @RequestPart MultipartFile file
    ) throws IOException {
        return service.save(dto, file);
    }

    @GetMapping("/getAll")
    public List<ManageTeam> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public ManageTeam getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public ManageTeam update(
            @PathVariable Long id,
            @RequestPart ManageTeamDto dto,
            @RequestPart(required = false) MultipartFile file
    ) throws IOException {
        return service.update(id, dto, file);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
