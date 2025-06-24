package org.example.construction.controller.home;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutDto;
import org.example.construction.model.About;
import org.example.construction.repository.HomeAboutRepository;
import org.example.construction.service.HomeService;
import org.example.construction.service.about.AboutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/v1/home/about")
@RestController
@RequiredArgsConstructor
public class AboutController {
    private final HomeService homeService;
    private final AboutService aboutService;
    private final HomeAboutRepository homeAboutRepository;

    @GetMapping
    public ResponseEntity<About>findAbout(){
        return ResponseEntity.ok(homeAboutRepository.findAll().getFirst());
    }

    @PostMapping
    public ResponseEntity<About> addAbout(@RequestPart(required = false, name = "request") AboutDto aboutDto,
                                          @RequestPart(required = false) MultipartFile aboutImage) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.createAbout(aboutDto, aboutImage));

    }

    @PutMapping("/{id}")
    public ResponseEntity<About> updateAbout(@PathVariable Long id,
                                             @RequestPart(required = false, name = "request") AboutDto aboutDto,
                                             @RequestPart(required = false) MultipartFile aboutImage) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(homeService.updateAbout(aboutDto, aboutImage, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbout(@PathVariable Long id) {
        homeService.deleteAbout(id);
        return ResponseEntity.noContent().build();
    }

}
