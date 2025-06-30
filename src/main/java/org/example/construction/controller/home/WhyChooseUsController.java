package org.example.construction.controller.home;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.model.Home;
import org.example.construction.model.WhyChooseUs;
import org.example.construction.repository.WhyChooseUsRepository;
import org.example.construction.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/whyChooseUs")
@RequiredArgsConstructor
public class WhyChooseUsController {
    private final HomeService homeService;
    private final WhyChooseUsRepository whyChooseUsRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<WhyChooseUs>> getAllWhyChooseUs() {
        return ResponseEntity.ok(whyChooseUsRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<WhyChooseUs> addWhyChooseUs(@RequestPart(required = false, name = "request") WhyChooseUsDto whyChooseUsDto,
                                                      @RequestPart(required = false) MultipartFile icon) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.createWhyChooseUs(whyChooseUsDto, icon));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WhyChooseUs> updateWhyChooseUs(@PathVariable Long id,
                                                         @RequestPart(required = false, name = "request") WhyChooseUsDto whyChooseUsDto,
                                                         @RequestPart(required = false) MultipartFile icon) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.updateWhyChooseUs(id, whyChooseUsDto, icon));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWhyChooseUs(@PathVariable Long id) {
        homeService.deleteWhyChooseUs(id);
        return ResponseEntity.noContent().build();
    }
}
