package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ApplicantDto;
import org.example.construction.dto.VacancyDto;
import org.example.construction.model.Applicant;
import org.example.construction.model.Vacancy;
import org.example.construction.repository.ApplicantRepository;
import org.example.construction.repository.VacancyRepository;
import org.example.construction.service.VacancyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final ApplicantRepository applicantRepository;
    private final VacancyRepository vacancyRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getById(@PathVariable int id) {
        return ResponseEntity.ok(vacancyService.findById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Vacancy> getBySlug(@PathVariable String slug) {
        return ResponseEntity.status(200).body(vacancyRepository.findBySlug(slug));
    }

    @GetMapping
    public ResponseEntity<List<Vacancy>> getAll() {
        return ResponseEntity.ok(vacancyService.findAll());
    }

    @GetMapping("/applicant")
    public ResponseEntity<List<Applicant>> getAllApplicants() {
        return ResponseEntity.ok(applicantRepository.findAll());
    }

    @GetMapping("/applicant/{id}")
    public ResponseEntity<Optional<Applicant>> getApplicant(@PathVariable int id) {
        return ResponseEntity.ok(applicantRepository.findById(id));
    }

    @DeleteMapping("/applicant/delete/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable int id) {
        applicantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/apply")
    public ResponseEntity<String> apply(@RequestPart(name = "request") ApplicantDto applicantDto, @RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyService.apply(applicantDto, file));
    }

    @PostMapping
    public ResponseEntity<Vacancy> create(@RequestPart(name = "request") VacancyDto vacancyDto,
                                          @RequestPart(required = false) List<MultipartFile> images) throws IOException {
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
