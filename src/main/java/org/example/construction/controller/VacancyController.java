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

    /// ======================= Vacancy ===========================

    @GetMapping("/get/{id}")
    public ResponseEntity<Vacancy> getById(@PathVariable int id) {
        return ResponseEntity.ok(vacancyService.findById(id));
    }

    @GetMapping("/getBySlug/{slug}")
    public ResponseEntity<Vacancy> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(vacancyRepository.findBySlug(slug));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Vacancy>> getAll() {
        return ResponseEntity.ok(vacancyService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Vacancy> add(
            @RequestPart(name = "request") VacancyDto vacancyDto,
            @RequestPart(required = false) MultipartFile image
    ) throws IOException {
        final var createdVacancy = vacancyService.save(vacancyDto, image);
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdVacancy.getId()).toUri();
        return ResponseEntity.created(uri).body(createdVacancy);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vacancy> update(
            @PathVariable int id,
            @RequestPart(name = "request") VacancyDto vacancyDto,
            @RequestPart(required = false) MultipartFile image
    ) {
        final var updatedVacancy = vacancyService.update(id, vacancyDto, image);
        return ResponseEntity.ok(updatedVacancy);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        vacancyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /// ======================= Applicant ===========================

    @GetMapping("/applicant/getAll")
    public ResponseEntity<List<Applicant>> getAllApplicants() {
        return ResponseEntity.ok(applicantRepository.findAll());
    }

    @GetMapping("/applicant/get/{id}")
    public ResponseEntity<Optional<Applicant>> getApplicant(@PathVariable int id) {
        return ResponseEntity.ok(applicantRepository.findById(id));
    }

    @PostMapping("/applicant/apply")
    public ResponseEntity<String> apply(
            @RequestPart(name = "request") ApplicantDto applicantDto,
            @RequestPart MultipartFile file
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyService.apply(applicantDto, file));
    }

    @DeleteMapping("/applicant/delete/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable int id) {
        applicantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
