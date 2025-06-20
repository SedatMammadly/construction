package org.example.construction.controller.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.VisionDto;
import org.example.construction.model.about.Vision;
import org.example.construction.service.about.VisionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vision")
@RequiredArgsConstructor
public class VisionController {

    private final VisionService service;

    @PostMapping
    public Vision create(@RequestBody VisionDto dto) {
        return service.save(dto);
    }

    @GetMapping
    public Vision get() {
        return service.get();
    }
}
