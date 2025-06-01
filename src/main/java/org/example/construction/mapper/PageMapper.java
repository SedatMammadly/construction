package org.example.construction.mapper;

import org.example.construction.dto.AboutUsRequest;
import org.example.construction.dto.HomeRequest;
import org.example.construction.dto.ProjectRequest;
import org.example.construction.dto.ProjectUpdateDto;
import org.example.construction.model.AboutUs;
import org.example.construction.model.Home;
import org.example.construction.model.Projects;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PageMapper {
    Home homeDtoToEntity(HomeRequest dto);
    AboutUs aboutUsDtoToEntity(AboutUsRequest dto);
    Projects projectDtoToEntity(ProjectRequest projectDto);
    Projects updateProjectEntityFromDto(@MappingTarget Projects projects, ProjectUpdateDto projectDto);
}
