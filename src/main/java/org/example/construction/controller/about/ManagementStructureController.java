package org.example.construction.controller.about;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ManagementStructureDto;
import org.example.construction.model.about.ManagementStructure;
import org.example.construction.service.about.ManagementStructureService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management-structure")
@RequiredArgsConstructor
public class ManagementStructureController {

    private final ManagementStructureService service;

    @PostMapping("/edit")
    public ManagementStructure create(@RequestBody ManagementStructureDto dto) {
        return service.save(dto);
    }

    @GetMapping("/get")
    public ManagementStructure get() {
        return service.get();
    }
}
