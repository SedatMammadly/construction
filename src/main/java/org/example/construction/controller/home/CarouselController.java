package org.example.construction.controller.home;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.CarouselDto;
import org.example.construction.model.Carousel;
import org.example.construction.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carousel")
@RequiredArgsConstructor
public class CarouselController {
    private final HomeService homeService;

    @GetMapping
    public ResponseEntity<List<Carousel>> getCarousel() {
        return ResponseEntity.ok(homeService.getCarousels());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carousel>updateCarousel(@PathVariable Long id, @RequestPart(name = "request") CarouselDto carouselDto,
                                                  @RequestPart(required = false) MultipartFile image)throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.updateCarousel(carouselDto,image,id));
    }


    @PostMapping
    public ResponseEntity<Carousel> create(@RequestPart(name = "request") CarouselDto dto, @RequestPart MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.createCarousel(dto,image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarousel(@PathVariable Long id) {
        homeService.deleteCarousel(id);
        return ResponseEntity.noContent().build();
    }

}
