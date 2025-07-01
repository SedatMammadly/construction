package org.example.construction.controller.about;

import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import org.example.construction.dto.HistoryDto;
import org.example.construction.model.about.History;
import org.example.construction.service.about.HistoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService service;

    @PostMapping("/edit")
    public History create(@RequestBody HistoryDto dto,@RequestHeader("Authorization") String authHeader) {
        System.out.println("Salam");
        return service.save(dto);
    }

    @GetMapping("/get")
    public History get() {
        return service.get();
    }
}
