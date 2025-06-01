package org.example.construction.mapper;

import org.example.construction.dto.AboutDto;
import org.example.construction.dto.MissionsDto;
import org.example.construction.dto.ValuesDto;
import org.example.construction.dto.WhyChooseUsDto;
import org.example.construction.model.Values;
import org.example.construction.pojo.About;
import org.example.construction.pojo.Missions;
import org.example.construction.pojo.WhyChooseUs;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PojoMapper {
    About aboutDtoToPojo(AboutDto dto);
    List<WhyChooseUs> whyChooseUsDtoListToPojoList(List<WhyChooseUsDto> dto);
    Missions missionsDtoToPojo(MissionsDto dto);
    Values valuesDtoToPojo(ValuesDto dto);
}
