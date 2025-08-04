package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.NewsDto;
import org.example.construction.dto.NewsUpdateDto;
import org.example.construction.model.News;
import org.example.construction.model.Special;
import org.example.construction.repository.NewsRepository;
import org.example.construction.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final NewsRepository newsRepository;

    @GetMapping("/get/{id}")
    public ResponseEntity<News> getById(@PathVariable int id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<News>> getAll() {
        return ResponseEntity.ok(newsRepository.findAllByOrderByCreatedAtDesc());    }

    @GetMapping("/get10News")
    public ResponseEntity<List<News>> getAllNews() {

        return ResponseEntity.ok(newsService.getAll());
    }

    @GetMapping("/getBySlug/{slug}")
    public ResponseEntity<News> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(newsRepository.findBySlug(slug));
    }

    @PostMapping("/add")
    public ResponseEntity<News> create(
            @RequestPart(name = "request") NewsDto newsDto,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        final var createdNews = newsService.save(newsDto, images);
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdNews.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdNews);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<News> update(
            @PathVariable int id,
            @RequestPart(name = "request") NewsUpdateDto newsUpdateDto,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        News updatedNews = newsService.update(id, newsUpdateDto, images);
        return ResponseEntity.status(201).body(updatedNews);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
