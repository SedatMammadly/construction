package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ProjectRequest;
import org.example.construction.dto.ProjectUpdateDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.model.Home;
import org.example.construction.model.Projects;
import org.example.construction.repository.HomeRepository;
import org.example.construction.repository.ProjectsRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectsRepository projectsRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;
    private final HomeRepository homeRepository;


    public List<Projects> getAllProjects() {
        return projectsRepository.findTop10ByOrderByCreatedAtDesc();
    }

    public Projects getProjectById(Integer id) {
        return projectsRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Projects addProject(ProjectRequest projectDto, MultipartFile file) throws IOException {
        Projects projects = pageMapper.projectDtoToEntity(projectDto);
        String slug = SlugUtil.toSlug(projects.getName());
        projects.setSlug(slug);
        if (file != null) {
            String image = fileService.uploadFile(file);
            projects.setImage(image);
        }
        return projectsRepository.save(projects);
    }

    public Projects updateProject(ProjectUpdateDto projectDto, MultipartFile file, Integer id) throws IOException {
        Projects projects = projectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Projects updatedProject = pageMapper.updateProjectEntityFromDto(projects, projectDto);
        String slug = SlugUtil.toSlug(updatedProject.getName());
        updatedProject.setSlug(slug);
        if (file != null) {
            fileService.removeFile(updatedProject.getImage());
            String image = fileService.uploadFile(file);
            updatedProject.setImage(image);
        }
        return projectsRepository.save(updatedProject);
    }


    public void deleteProject(Integer id) {
        Projects projects = projectsRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        if (projects.getImage() != null) {
            fileService.removeFile(projects.getImage());
        }
        projectsRepository.deleteById(id);
    }

    public Projects getProjectBySlug(String slug) {
        return projectsRepository.findBySlug(slug);
    }
}
