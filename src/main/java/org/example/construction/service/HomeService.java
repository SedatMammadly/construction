package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutDto;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Home;
import org.example.construction.model.About;
import org.example.construction.model.WhyChooseUs;
import org.example.construction.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {
     private final NewsRepository newsRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;
    private final PojoMapper pojoMapper;
    private final ProjectsRepository projectsRepository;
    private final HomeAboutRepository homeAboutRepository;
    private final WhyChooseUsRepository whyChooseUsRepository;

    public Home getHome() {
        Home home = new Home();
        List<WhyChooseUs>whyChooseUsList = whyChooseUsRepository.findAll();
        home.setProjects(projectsRepository.findTop10ByOrderByCreatedAtDesc());
        home.setAbout(homeAboutRepository.findAll().getFirst());
        home.setNews(newsRepository.findTop10ByOrderByCreatedAtDesc());
        home.setWhyChooseUs(whyChooseUsList);
        return home;
    }

    public About createAbout(AboutDto aboutDto, MultipartFile aboutImage) throws IOException {
        About about = pojoMapper.aboutDtoToPojo(aboutDto);
        if (aboutImage != null) {
            String url = fileService.uploadFile(aboutImage);
            about.setImage(url);
        }
        return homeAboutRepository.save(about);
    }

    public About updateAbout(AboutDto aboutDto, MultipartFile aboutImage, Long id) throws IOException {
        About about = homeAboutRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (aboutDto != null) {
            about = pojoMapper.aboutDtoToPojo(aboutDto);
        }
        if (aboutImage != null) {
            String image = about.getImage();
            if (image != null) {
                fileService.removeFile(image);
            }
            String url = fileService.uploadFile(aboutImage);
            about.setImage(url);
        }
        return homeAboutRepository.save(about);
    }

    public void deleteAbout(Long id) {
        About about = homeAboutRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        String image = about.getImage();
        if (image != null) {
            fileService.removeFile(image);
        }
        homeAboutRepository.deleteById(id);
    }

    public WhyChooseUs createWhyChooseUs(WhyChooseUsDto whyChooseUsDto, MultipartFile icon) throws IOException {
        WhyChooseUs whyChooseUs = pojoMapper.whyChooseUsDtoToPojo(whyChooseUsDto);
        if (icon != null) {
            String url = fileService.uploadFile(icon);
            whyChooseUs.setIcon(url);
        }
        return whyChooseUsRepository.save(whyChooseUs);
    }


    public WhyChooseUs updateWhyChooseUs(Long id, WhyChooseUsDto whyChooseUsDto, MultipartFile icon) throws IOException {
        WhyChooseUs whyChooseUs = whyChooseUsRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (whyChooseUsDto != null) {
            pojoMapper.updateWhyChooseUs(whyChooseUs, whyChooseUsDto);
        }
        if (icon != null) {
            String icon1 = whyChooseUs.getIcon();
            if (icon1 != null) {
                fileService.removeFile(icon1);
            }
            String url = fileService.uploadFile(icon);
            whyChooseUs.setIcon(url);
        }
        return whyChooseUsRepository.save(whyChooseUs);
    }

    public void deleteWhyChooseUs(Long id) {
        WhyChooseUs whyChooseUs = whyChooseUsRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        String icon = whyChooseUs.getIcon();
        if (icon != null) {
            fileService.removeFile(icon);
        }
        whyChooseUsRepository.deleteById(id);
    }
}
