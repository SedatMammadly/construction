package org.example.construction.controller.about;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ValuesDto;
import org.example.construction.model.about.Values;
import org.example.construction.service.about.ValuesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/values")
@RequiredArgsConstructor
public class ValuesController {

    private final ValuesService service;

    @PostMapping("/add")
    public Values add(
            @RequestPart ValuesDto dto,
            @RequestPart MultipartFile file
    ) throws IOException {
        return service.save(dto, file);
    }

    @GetMapping("/getAll")
    public List<Values> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Values getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Values update(
            @PathVariable Long id,
            @RequestPart ValuesDto dto,
            @RequestPart MultipartFile file
    ) throws IOException {
        return service.update(id, dto, file);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
