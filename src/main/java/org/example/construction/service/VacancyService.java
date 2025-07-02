package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.construction.dto.ApplicantDto;
import org.example.construction.dto.VacancyDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.model.Applicant;
import org.example.construction.model.Vacancy;
import org.example.construction.repository.ApplicantRepository;
import org.example.construction.repository.VacancyRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final PageMapper pageMapper;
    private final FileService fileService;
    private final ApplicantRepository applicantRepository;

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    public Vacancy findById(int id) {
        return vacancyRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacancy not found"));
    }

    public Vacancy save(VacancyDto vacancyDto, MultipartFile image) throws IOException {
        Vacancy vacancy = pageMapper.vacancyEntityToDto(vacancyDto);
        vacancy.setSlug(SlugUtil.toSlug(vacancy.getTitle()));
        if (image != null) {
            String file = fileService.uploadFile(image);
            vacancy.setImage(file);
        }
        return vacancyRepository.save(vacancy);
    }

    @SneakyThrows
    public Vacancy update(int id, VacancyDto vacancyDto, MultipartFile image) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacancy not found"));
        pageMapper.updateVacancyEntityFromDto(vacancy, vacancyDto);
        vacancy.setSlug(SlugUtil.toSlug(vacancy.getTitle()));
        if (image != null) {
            fileService.deleteFile(vacancy.getImage());
            String file = fileService.uploadFile(image);
            vacancy.setImage(file);
        }
        return vacancyRepository.save(vacancy);
    }

    public void delete(int id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacancy not found"));
        fileService.deleteFile(vacancy.getImage());
        vacancyRepository.deleteById(id);
    }

    public String apply(ApplicantDto applicantDto, MultipartFile file) throws IOException {
        Applicant applicant = pageMapper.applicantDtoToEntity(applicantDto);
        if (file != null) {
            String fileName = fileService.uploadFile(file);
            applicant.setCvFile(fileName);
        }
        applicantRepository.save(applicant);
        return "successfully applied";
    }
}
