package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.NewsDto;
import org.example.construction.dto.NewsUpdateDto;
import org.example.construction.model.News;
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

    @GetMapping("/{id}")
    public ResponseEntity<News> getById(@PathVariable int id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<News>> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @PostMapping
    public ResponseEntity<News> create(@RequestPart(name = "request") NewsDto newsDto,
                                       @RequestPart(required = false) List<MultipartFile> images) throws IOException {
        final var createdNews = newsService.save(newsDto, images);
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdNews.getId()).toUri();
        return ResponseEntity.created(uri).body(createdNews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> update(@PathVariable int id, @RequestPart(name = "request") NewsUpdateDto newsUpdateDto,
                                       @RequestPart(required = false) List<MultipartFile> images) throws IOException {
        final var updatedNews = newsService.update(id, newsUpdateDto, images);
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(updatedNews.getId()).toUri();
        return ResponseEntity.created(uri).body(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
