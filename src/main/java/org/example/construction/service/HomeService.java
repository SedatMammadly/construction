package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.construction.dto.AboutDto;
import org.example.construction.dto.CarouselDto;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Carousel;
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
    private final CarouselRepository carouselRepository;

    public Home getHome() {
        Home home = new Home();
        List<WhyChooseUs> whyChooseUsList = whyChooseUsRepository.findAll();
        home.setProjects(projectsRepository.findTop10ByOrderByCreatedAtDesc());
        home.setAbout(homeAboutRepository.findAll().getFirst());
        home.setNews(newsRepository.findTop10ByOrderByCreatedAtDesc());
        home.setWhyChooseUs(whyChooseUsList);
        home.setCarousel(carouselRepository.findAll());
        return home;
    }

    public About createAbout(AboutDto aboutDto) {
        About about = new About();
        about.setTitle(aboutDto.getTitle());
        about.setDescription(aboutDto.getParagraph());
        return homeAboutRepository.save(about);
    }

    public About updateAbout(AboutDto aboutDto, Long id) {
        About about = homeAboutRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        about.setTitle(aboutDto.getTitle());
        about.setDescription(aboutDto.getParagraph());
        return homeAboutRepository.save(about);
    }

    public void deleteAbout(Long id) {
        About about = homeAboutRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

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
                fileService.deleteFile(icon1);
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
            fileService.deleteFile(icon);
        }
        whyChooseUsRepository.deleteById(id);
    }

    public List<Carousel> getCarousels() {
        return carouselRepository.findAll();
    }

    @SneakyThrows
    public Carousel updateCarousel(CarouselDto carouselDto, MultipartFile carouselImage, Long id) {
        Carousel carousel = carouselRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        pojoMapper.updateCarousel(carousel, carouselDto);
        if (carouselImage != null) {
            fileService.deleteFile(carousel.getImage());
            String image = fileService.uploadFile(carouselImage);
            carousel.setImage(image);
        }
        return carouselRepository.save(carousel);
    }


    @SneakyThrows
    public Carousel createCarousel(CarouselDto dto, MultipartFile image) {
        Carousel carousel = pojoMapper.carouselDtoToPojo(dto);
        if (image != null) {
            String imageUrl = fileService.uploadFile(image);
            carousel.setImage(imageUrl);
        }
        return carouselRepository.save(carousel);
    }

    public void deleteCarousel(Long id) {
        Carousel carousel = carouselRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (carousel.getImage() != null) {
            fileService.deleteFile(carousel.getImage());
        }
        carouselRepository.deleteById(id);
    }


}
