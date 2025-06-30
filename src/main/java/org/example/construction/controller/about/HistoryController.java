package org.example.construction.controller.about;

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
    public History create(@RequestBody HistoryDto dto) {
        return service.save(dto);
    }

    @GetMapping("/get")
    public History get() {
        return service.get();
    }
}
