package org.example.construction.mapper;

import org.example.construction.dto.*;
import org.example.construction.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PageMapper {
    Home homeDtoToEntity(HomeRequest dto);
    AboutUs aboutUsDtoToEntity(AboutUsRequest dto);
    Projects projectDtoToEntity(ProjectRequest projectDto);
    Projects updateProjectEntityFromDto(@MappingTarget Projects projects, ProjectUpdateDto projectDto);
    News newsDtoToEntity(NewsDto dto);
    News updateNewsEntityFromDto(@MappingTarget News news, NewsUpdateDto dto);
    Vacancy vacancyEntityToDto(VacancyDto vacancyDto);
    Vacancy updateVacancyEntityFromDto(@MappingTarget Vacancy vacancy, VacancyDto vacancyDto);
    Applicant applicantDtoToEntity(ApplicantDto applicantDto);
    ContactMessage contactMessageDtoToEntity(ContactMessageDto contactMessageDto);
}
