package org.example.construction.controller.home;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutDto;
import org.example.construction.model.About;
import org.example.construction.model.Home;
import org.example.construction.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/v1/home/about")
@RestController
@RequiredArgsConstructor
public class AboutController {
    private final HomeService homeService;

    @PostMapping
    public ResponseEntity<About> addAbout ( @RequestPart(required = false, name = "request") AboutDto aboutDto,
                                            @RequestPart(required = false) MultipartFile aboutImage) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.createAbout(aboutDto, aboutImage));

    }

    @PutMapping("/{id}")
    public ResponseEntity<About> updateAbout(@PathVariable Long id,
                                             @RequestPart(required = false, name = "request") AboutDto aboutDto,
                                             @RequestPart(required = false) MultipartFile aboutImage) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.updateAbout(aboutDto,aboutImage,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteAbout(@PathVariable Long id) {
        homeService.deleteAbout(id);
        return ResponseEntity.noContent().build();
    }

}
