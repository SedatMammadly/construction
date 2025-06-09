package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutDto;
import org.example.construction.dto.HomeRequest;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Home;
import org.example.construction.pojo.About;
import org.example.construction.pojo.WhyChooseUs;
import org.example.construction.repository.HomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;
    private final PojoMapper pojoMapper;

    public Home getHome() {
        return homeRepository.findAll().getFirst();
    }

    public Home createHome(HomeRequest homeRequest, MultipartFile aboutImage, List<MultipartFile> icons) throws IOException {
        Home home = pageMapper.homeDtoToEntity(homeRequest);
        home.setProjects(new ArrayList<>());
        home.setNews(new ArrayList<>());
        if (icons != null) {
            for (int i = 0; i < icons.size(); i++) {
                String url = fileService.uploadFile(icons.get(i));
                home.getWhyChooseUs().get(i).setIcon(url);
            }
        }
        if (aboutImage != null) {
            String url = fileService.uploadFile(aboutImage);
            home.getAbout().setImage(url);
        }
        return homeRepository.save(home);
    }

    public Home updateAbout(AboutDto aboutDto, MultipartFile aboutImage) throws IOException {
        Home home = homeRepository.findAll().getFirst();
        if (aboutDto != null) {
            About about = pojoMapper.aboutDtoToPojo(aboutDto);
            home.setAbout(about);
        }
        if (aboutImage != null) {
            String image = home.getAbout().getImage();
            if ( image != null){
                fileService.removeFile(image);
            }
            String url = fileService.uploadFile(aboutImage);
            home.getAbout().setImage(url);
        }
        return homeRepository.save(home);
    }

    public Home updateWhyChooseUs(List<WhyChooseUsDto> whyChooseUsDtoList, List<MultipartFile> icons) throws IOException {
        Home home = homeRepository.findAll().getFirst();
        if (!whyChooseUsDtoList.isEmpty()) {
            home.getWhyChooseUs().clear();
            List<WhyChooseUs> whyChooseUs = pojoMapper.whyChooseUsDtoListToPojoList(whyChooseUsDtoList);
            home.setWhyChooseUs(whyChooseUs);
        }
        if (icons != null) {
            for (int i = 0; i < icons.size(); i++) {
                String url = fileService.uploadFile(icons.get(i));
                home.getWhyChooseUs().get(i).setIcon(url);
            }
        }
        return homeRepository.save(home);
    }

    public Home updateWhyChooseUs(int index, WhyChooseUsDto whyChooseUsDto, MultipartFile icon) throws IOException {
        Home home = homeRepository.findAll().getFirst();
        int realIndex = index - 1;
        if (whyChooseUsDto != null) {
            home.getWhyChooseUs().get(realIndex).setParagraph(whyChooseUsDto.getParagraph());
            home.getWhyChooseUs().get(realIndex).setTitle(whyChooseUsDto.getTitle());
        }
        if (icon != null) {
            String icon1 = home.getWhyChooseUs().get(realIndex).getIcon();
            if (icon1 != null) {
                fileService.removeFile(icon1);
            }
            String url = fileService.uploadFile(icon);
            home.getWhyChooseUs().get(realIndex).setIcon(url);
        }
        return homeRepository.save(home);
    }

    public Home deleteWhyChooseUs(int index) {
        int realIndex = index - 1;
        Home home = homeRepository.findAll().getFirst();
        String icon = home.getWhyChooseUs().get(realIndex).getIcon();
        if (icon != null) {
            fileService.removeFile(icon);
        }
        home.getWhyChooseUs().remove(realIndex);
        return homeRepository.save(home);
    }
}
