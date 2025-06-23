package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ProjectRequest;
import org.example.construction.dto.ProjectUpdateDto;
import org.example.construction.model.Projects;
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

    @GetMapping("{id}")
    public ResponseEntity<Projects> getProjectsById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<Projects> getProjectBySlug(@RequestParam String slug) {
        return ResponseEntity.ok(projectService.getProjectBySlug(slug));
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Projects>> getProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<Projects> addProject(@RequestPart("request") ProjectRequest request,
                                               @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        Projects project = projectService.addProject(request, images);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projects> updateProject(@PathVariable Integer id,
                                                  @RequestPart("request") ProjectRequest request,
                                                  @RequestPart(required = false) List<MultipartFile> image
    ) throws IOException {
        Projects project = projectService.updateProject(id,request, image );
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully.");
    }

}
