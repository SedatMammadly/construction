package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutDto;
import org.example.construction.dto.HomeRequest;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.model.Home;
import org.example.construction.pojo.WhyChooseUs;
import org.example.construction.service.HomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping
    public ResponseEntity<Home> getHome() {
        return ResponseEntity.ok(homeService.getHome());
    }

    @PostMapping
    public ResponseEntity<Home> createHome(@RequestPart HomeRequest request,
                                           @RequestPart(required = false) MultipartFile aboutImage,
                                           @RequestPart(required = false) List<MultipartFile> icons) throws IOException {
        return ResponseEntity.ok(homeService.createHome(request, aboutImage, icons));
    }

    @PutMapping("/update/about")
    public ResponseEntity<Home> updateAbout(@RequestPart(required = false,name = "request") AboutDto aboutDto,
                                            @RequestPart(required = false) MultipartFile aboutImage) throws IOException {
        return ResponseEntity.ok(homeService.updateAbout(aboutDto, aboutImage));
    }

    @PutMapping("/update/whyChooseUs/{index}")
    public ResponseEntity<Home> updateWhyChooseUs(@PathVariable int index,@RequestPart(required = false,name = "request") WhyChooseUsDto whyChooseUsDto,
                                                  @RequestPart(required = false)MultipartFile icon) throws IOException {
        return ResponseEntity.ok(homeService.updateWhyChooseUs(index,whyChooseUsDto,icon));
    }

    @DeleteMapping("/delete/whyChooseUs/{index}")
    public ResponseEntity<Home> deleteWhyChooseUs(@PathVariable int index) {
        return ResponseEntity.ok(homeService.deleteWhyChooseUs(index));
    }
}
