package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ForeignMissionDto;
import org.example.construction.model.foreign.ForeignMission;
import org.example.construction.repository.foreign.ForeignRepository;
import org.example.construction.service.foreign.ForeignMissionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/foreign")
@RequiredArgsConstructor
public class ForeignMissionsController {
    private final ForeignRepository foreignRepository;
    private final ForeignMissionService foreignMissionService;

    @GetMapping("/all")
    public List<ForeignMission> getAllForeignMissions() {
        return foreignRepository.findAll();
    }

    @GetMapping("/{id}")
    public ForeignMission getForeignMission(@PathVariable Long id) {
        return foreignRepository.findById(id).get();
    }

    @PostMapping("/add")
    public ForeignMission addForeignMission(@RequestPart ForeignMissionDto foreignMissionDto, List<MultipartFile> images, MultipartFile icon) throws IOException {
        return foreignMissionService.add(foreignMissionDto, images, icon);
    }

    // 🔎 GET by Slug
    @GetMapping("/slug/{slug}")
    public ForeignMission getBySlug(@PathVariable String slug) {
        return foreignRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Foreign mission not found by slug"));
    }

    // ❌ DELETE by ID
    @DeleteMapping("/{id}")
    public void deleteForeignMission(@PathVariable Long id) {
        foreignRepository.deleteById(id);
    }

    // ♻️ UPDATE by ID
    @PutMapping("/{id}")
    public ForeignMission updateForeignMission(@PathVariable Long id,
                                               @RequestPart ForeignMissionDto foreignMissionDto,
                                               @RequestPart(required = false) List<MultipartFile> images,
                                               @RequestPart(required = false) MultipartFile icon) throws IOException {
        return foreignMissionService.update(id, foreignMissionDto, images, icon);
    }
}
