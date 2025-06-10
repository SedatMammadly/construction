package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ApplicantDto;
import org.example.construction.dto.VacancyDto;
import org.example.construction.mapper.PageMapper;
import org.example.construction.model.Applicant;
import org.example.construction.model.Vacancy;
import org.example.construction.repository.ApplicantRepository;
import org.example.construction.repository.VacancyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Vacancy save(VacancyDto vacancyDto, List<MultipartFile> images) throws IOException {
        Vacancy vacancy = pageMapper.vacancyEntityToDto(vacancyDto);
        List<String> imageList = new ArrayList<>();
        if (images != null){
            for (MultipartFile image : images) {
                String file = fileService.uploadFile(image);
                imageList.add(file);
            }
        }
        vacancy.setImages(imageList);
        return vacancyRepository.save(vacancy);
    }

    public Vacancy update(int id,VacancyDto vacancyDto, List<MultipartFile> images) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacancy not found"));
        Vacancy savedVacancy = pageMapper.updateVacancyEntityFromDto(vacancy,vacancyDto);
        List<String> imageList = savedVacancy.getImages();
     fileService.deleteFiles(imageList);

        return vacancyRepository.save(savedVacancy);
    }

    public void delete(int id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacancy not found"));
        for (String image : vacancy.getImages()) {
            fileService.removeFile(image);
        }
        vacancyRepository.deleteById(id);
    }

    public String apply(ApplicantDto applicantDto, MultipartFile file) throws IOException {
        Applicant applicant = pageMapper.applicantDtoToEntity(applicantDto);
        if (file != null){
            String fileName = fileService.uploadFile(file);
            applicant.setCvFile(fileName);
        }
        applicantRepository.save(applicant);
        return "successfully applied";
    }
}
