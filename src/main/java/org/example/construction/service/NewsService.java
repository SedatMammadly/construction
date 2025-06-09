package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.NewsDto;
import org.example.construction.dto.NewsUpdateDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.model.Home;
import org.example.construction.model.News;
import org.example.construction.pojo.HomeNews;
import org.example.construction.repository.HomeRepository;
import org.example.construction.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;
    private final HomeRepository homeRepository;

    public List<News> getAll() {
        return newsRepository.findAll();
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
        Home home = homeRepository.findAll().getFirst();
        home.getNews().add(news);
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
                fileService.removeFile(image);
            }
        }
        fileService.deleteFiles(oldImages);


        news.setImages(oldImages);
        Home home = homeRepository.findAll().getFirst();
        List<News> newsList = home.getNews();
        newsList.removeIf(item -> item.getId() == id);
        newsList.add(news);
        home.setNews(newsList);
        homeRepository.save(home);
        return newsRepository.save(savedNews);
    }

    public void deleteById(int id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
        Home home = homeRepository.findAll().getFirst();
        List<News> newsList = home.getNews();
        newsList.removeIf(item -> item.getId() == id);
        for (String image : news.getImages()) {
            fileService.removeFile(image);
        }
        newsRepository.deleteById(id);
    }
}
