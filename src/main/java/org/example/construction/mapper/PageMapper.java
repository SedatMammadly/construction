package org.example.construction.mapper;

import org.example.construction.dto.AboutUsRequest;
import org.example.construction.dto.HomeRequest;
import org.example.construction.model.AboutUs;
import org.example.construction.model.Home;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PageMapper {
    Home homeDtoToEntity(HomeRequest dto);
    AboutUs aboutUsDtoToEntity(AboutUsRequest dto);
}
