package org.example.construction.controller.home;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.model.Home;
import org.example.construction.model.WhyChooseUs;
import org.example.construction.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/whyChooseUs")
@RequiredArgsConstructor
public class WhyChooseUsController {
    private final HomeService homeService;

    @PostMapping
    public ResponseEntity<WhyChooseUs> addWhyChooseUs(@RequestPart(required = false,name = "request") WhyChooseUsDto whyChooseUsDto,
                                                         @RequestPart(required = false) MultipartFile icon) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.createWhyChooseUs(whyChooseUsDto,icon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WhyChooseUs> updateWhyChooseUs(@PathVariable Long id, @RequestPart(required = false,name = "request") WhyChooseUsDto whyChooseUsDto,
                                                         @RequestPart(required = false) MultipartFile icon) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.updateWhyChooseUs(id,whyChooseUsDto,icon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWhyChooseUs(@PathVariable Long id) {
        homeService.deleteWhyChooseUs(id);
        return ResponseEntity.noContent().build();
    }
}
