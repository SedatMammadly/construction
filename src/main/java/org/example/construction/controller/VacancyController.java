package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ApplicantDto;
import org.example.construction.dto.VacancyDto;
import org.example.construction.model.Vacancy;
import org.example.construction.service.VacancyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getById(@PathVariable int id) {
        return ResponseEntity.ok(vacancyService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Vacancy>> getAll() {
        return ResponseEntity.ok(vacancyService.findAll());
    }

    @PostMapping("/apply")
    public ResponseEntity<String> apply(@RequestPart(name = "request") ApplicantDto applicantDto, @RequestPart MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyService.apply(applicantDto,file));
    }

    @PostMapping
    public ResponseEntity<Vacancy> create(@RequestPart(name = "request") VacancyDto vacancyDto,
                                       @RequestPart(required = false) List<MultipartFile> images) {
        final var createdVacancy = vacancyService.save(vacancyDto, images);
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdVacancy.getId()).toUri();
        return ResponseEntity.created(uri).body(createdVacancy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacancy> update(@PathVariable int id, @RequestPart(name = "request") VacancyDto vacancyDto,
                                       @RequestPart(required = false) List<MultipartFile> images) {
        final var updatedVacancy = vacancyService.update(id, vacancyDto, images);
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(updatedVacancy.getId()).toUri();
        return ResponseEntity.created(uri).body(updatedVacancy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        vacancyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
