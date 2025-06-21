package org.example.construction.controller.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.model.about.About;
import org.example.construction.service.about.AboutService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
public class AboutUsController {
    private final AboutService aboutService;

    @GetMapping
    public About getAbout() {
        return aboutService.get();
    }
}
