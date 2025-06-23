package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ProjectRequest;
import org.example.construction.model.Projects;
import org.example.construction.repository.ProjectsRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectsRepository projectsRepository;
    private final FileService fileService;

    public Projects addProject(ProjectRequest projectDto, List<MultipartFile> images) throws IOException {
        Projects project = new Projects();
        project.setName(projectDto.getName());
        project.setContent(projectDto.getContent());
        project.setConstructDate(projectDto.getContructDate());
        project.setSlug(SlugUtil.toSlug(projectDto.getName()));
        project.setOrderOwner(projectDto.getOrderOwner());
        project.setCreatedAt(LocalDateTime.now());

        if (images != null && !images.isEmpty()) {
            project.setImages(fileService.uploadFiles(images));
        }

        return projectsRepository.save(project);
    }

    public Projects updateProject(Integer id, ProjectRequest projectDto, List<MultipartFile> images) throws IOException {
        Projects existingProject = projectsRepository.findById(id).orElseThrow();

        // köhnə şəkilləri sil
        if (existingProject.getImages() != null) {
            fileService.deleteFiles(existingProject.getImages());
        }

        existingProject.setName(projectDto.getName());
        existingProject.setContent(projectDto.getContent());
        existingProject.setConstructDate(projectDto.getContructDate());
        existingProject.setSlug(SlugUtil.toSlug(projectDto.getName()));
        existingProject.setOrderOwner(projectDto.getOrderOwner());

        if (images != null && !images.isEmpty()) {
            existingProject.setImages(fileService.uploadFiles(images));
        }

        return projectsRepository.save(existingProject);
    }

    public void deleteProject(Integer id) {
        Optional<Projects> optionalProject = projectsRepository.findById(id);
        if (optionalProject.isPresent()) {
            Projects project = optionalProject.get();

            if (project.getImages() != null) {
                fileService.deleteFiles(project.getImages());
            }

            projectsRepository.deleteById(id);
        }
    }

    public Projects getProjectById(Integer id) {
        return projectsRepository.findById(id).orElseThrow();
    }

    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }


    public Projects getProjectBySlug(String slug) {
        return projectsRepository.findBySlug(slug);
    }
}
