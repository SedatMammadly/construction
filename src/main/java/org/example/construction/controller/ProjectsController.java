package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ProjectRequest;
import org.example.construction.dto.ProjectUpdateDto;
import org.example.construction.model.Projects;
import org.example.construction.repository.ProjectsRepository;
import org.example.construction.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectsController {
    private final ProjectService projectService;
    private final ProjectsRepository projectsRepository;

    @GetMapping("/get/{id}")
    public ResponseEntity<Projects> getProjectsById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/getBySlug/{slug}")
    public ResponseEntity<Projects> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(projectsRepository.findBySlug(slug));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Projects>> getProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping("/add")
    public ResponseEntity<Projects> addProject(
            @RequestPart("request") ProjectRequest request,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        Projects project = projectService.addProject(request, images);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Projects> updateProject(
            @PathVariable Integer id,
            @RequestPart("request") ProjectUpdateDto projectUpdateDto,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        Projects project = projectService.updateProject(id, projectUpdateDto, images);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully.");
    }
}
