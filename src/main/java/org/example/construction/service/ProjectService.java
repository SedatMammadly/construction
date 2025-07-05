package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ProjectRequest;
import org.example.construction.dto.ProjectUpdateDto;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Projects;
import org.example.construction.repository.ProjectsRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectsRepository projectsRepository;
    private final FileService fileService;
    private final PojoMapper pojoMapper;

    public Projects addProject(ProjectRequest projectUpdateDto, List<MultipartFile> images) throws IOException {
        Projects project = new Projects();
        project.setName(projectUpdateDto.getName());
        project.setContent(projectUpdateDto.getContent());
        project.setConstructDate(projectUpdateDto.getConstructDate());
        project.setSlug(SlugUtil.toSlug(projectUpdateDto.getName()));
        project.setOrderOwner(projectUpdateDto.getOrderOwner());
        project.setCreatedAt(LocalDateTime.now());

        if (images != null && !images.isEmpty()) {
            project.setImages(fileService.uploadFiles(images));
        }

        return projectsRepository.save(project);
    }

    public Projects updateProject(Integer id, ProjectUpdateDto projectUpdateDto, List<MultipartFile> images) throws IOException {
        Projects existingProject = projectsRepository.findById(id).orElseThrow(()->new RuntimeException("Project not found"));
        existingProject.setName(projectUpdateDto.getName());
        existingProject.setContent(projectUpdateDto.getContent());
        existingProject.setConstructDate(projectUpdateDto.getConstructDate());
        existingProject.setSlug(SlugUtil.toSlug(projectUpdateDto.getName()));
        existingProject.setOrderOwner(projectUpdateDto.getOrderOwner());
        List<String>toRemove = new ArrayList<>();

        for(String image : existingProject.getImages()) {
            if(!projectUpdateDto.getImages().contains(image)) {
                toRemove.add(image);
            }
        }

        fileService.deleteFiles(toRemove);
        existingProject.setImages(projectUpdateDto.getImages());
        
        if (images != null && !images.isEmpty()) {
            existingProject.getImages().addAll(fileService.uploadFiles(images));
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
