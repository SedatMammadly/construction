package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.AboutDto;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.model.Home;
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


}
