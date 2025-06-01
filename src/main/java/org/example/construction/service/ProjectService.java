package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ProjectRequest;
import org.example.construction.dto.ProjectUpdateDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.model.Home;
import org.example.construction.model.Projects;
import org.example.construction.pojo.HomeProjects;
import org.example.construction.repository.HomeRepository;
import org.example.construction.repository.ProjectsRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectsRepository projectsRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;
    private final HomeRepository homeRepository;


    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    public Projects getProjectById(Integer id) {
        return projectsRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Projects addProject(ProjectRequest projectDto, MultipartFile file) {
        Projects projects = pageMapper.projectDtoToEntity(projectDto);
        String slug = SlugUtil.toSlug(projects.getName());
        projects.setSlug(slug);
        if (file != null) {
            String image = fileService.uploadFile(file);
            projects.setImage(image);
        }
        Home home = homeRepository.findAll().getFirst();
        HomeProjects homeProjects = new HomeProjects();
        homeProjects.setProjectSlug(slug);
        homeProjects.setImage(projects.getImage());
        home.getProjects().add(homeProjects);
        homeRepository.save(home);
        return projectsRepository.save(projects);
    }

    public Projects updateProject(ProjectUpdateDto projectDto, MultipartFile file, Integer id) {
        Projects projects = projectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        String oldSlug = projects.getSlug();
        Projects updatedProject = pageMapper.updateProjectEntityFromDto(projects, projectDto);

        String slug = SlugUtil.toSlug(updatedProject.getName());
        updatedProject.setSlug(slug);

        if (file != null) {
            fileService.removeFile(updatedProject.getImage());
            String image = fileService.uploadFile(file);
            updatedProject.setImage(image);
        }

        Projects savedProject = projectsRepository.save(updatedProject);
        Home home = homeRepository.findAll().getFirst();

        home.getProjects().removeIf(hp -> hp.getProjectSlug().equals(oldSlug));
        for(HomeProjects hp : home.getProjects()) {
            System.out.println(hp.getProjectSlug());
            System.out.println(hp.getProjectSlug() + "is equal  " + oldSlug + "  "+ oldSlug.equals(hp.getProjectSlug()));
        }
        HomeProjects homeProjects = new HomeProjects();
        homeProjects.setProjectSlug(savedProject.getSlug());
        homeProjects.setImage(savedProject.getImage());
        home.getProjects().add(homeProjects);
        homeRepository.save(home);
        return savedProject;
    }


    public void deleteProject(Integer id) {
        Projects projects = projectsRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        if (projects.getImage() != null) {
            fileService.removeFile(projects.getImage());
        }
        Home home = homeRepository.findAll().getFirst();
        home.getProjects().removeIf(homeProjects -> homeProjects.getProjectSlug().equals(projects.getSlug()));
        homeRepository.save(home);
        projectsRepository.deleteById(id);
    }

    public Projects getProjectBySlug(String slug) {
        return projectsRepository.findBySlug(slug);
    }
}
