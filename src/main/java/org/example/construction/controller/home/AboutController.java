package org.example.construction.controller.home;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutDto;
import org.example.construction.model.About;
import org.example.construction.repository.HomeAboutRepository;
import org.example.construction.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

 import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/home/about")
@RestController
@RequiredArgsConstructor
public class AboutController {
    private final HomeService homeService;
    private final HomeAboutRepository homeAboutRepository;

    @GetMapping("/get")
    public ResponseEntity<List<About>> findAbout() {
        return ResponseEntity.ok(homeAboutRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<About> addAbout(@RequestPart(required = false, name = "request") AboutDto aboutDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.createAbout(aboutDto));

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<About>> getByAboutId(@PathVariable Long id) {
        return ResponseEntity.ok(homeAboutRepository.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<About> updateAbout(@PathVariable Long id,
                                             @RequestPart(required = false, name = "request") AboutDto aboutDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.updateAbout(aboutDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAbout(@PathVariable Long id) {
        homeService.deleteAbout(id);
        return ResponseEntity.noContent().build();
    }

}
