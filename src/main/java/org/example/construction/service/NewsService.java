package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.NewsDto;
import org.example.construction.dto.NewsUpdateDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.model.News;
 import org.example.construction.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;

    public List<News> getAll() {
        return newsRepository.findTop10ByOrderByCreatedAtDesc();
    }

    public News getById(int id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    }

    public News save(NewsDto newsDto, List<MultipartFile> images) throws IOException {
        News news = pageMapper.newsDtoToEntity(newsDto);
        List<String> imageList = new ArrayList<>();
        if (images != null) {
            for (MultipartFile file : images) {
                String image = fileService.uploadFile(file);
                imageList.add(image);
            }
            news.setImages(imageList);
        }
        return newsRepository.save(news);
    }

    public News update(int id, NewsUpdateDto newsUpdateDto, List<MultipartFile> images) throws IOException {
        News news = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
        List<String> oldImages = news.getImages();
        List<String> toRemove = new ArrayList<>();
        News savedNews = pageMapper.updateNewsEntityFromDto(news, newsUpdateDto);
        for (String image : oldImages) {
            if (!newsUpdateDto.getImages().contains(image)) {
                toRemove.add(image);
                oldImages.remove(image);
            }
        }
        fileService.deleteFiles(toRemove);
        for (MultipartFile image : images){
            if (image!=null){
                String imageName=fileService.uploadFile(image);
                oldImages.add(imageName);
            }
        }

        news.setImages(oldImages);
        return newsRepository.save(savedNews);
    }

    public void deleteById(int id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
        for (String image : news.getImages()) {
            fileService.removeFile(image);
        }
        newsRepository.deleteById(id);
    }
}
