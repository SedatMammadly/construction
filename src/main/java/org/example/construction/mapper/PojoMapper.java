package org.example.construction.mapper;

import org.example.construction.dto.*;
import org.example.construction.model.About;
import org.example.construction.model.WhyChooseUs;
import org.example.construction.model.about.ManageTeam;
import org.example.construction.model.about.Missions;
import org.example.construction.model.about.Values;
import org.example.construction.pojo.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PojoMapper {
    About aboutDtoToPojo(AboutDto dto);
    WhyChooseUs whyChooseUsDtoToPojo(WhyChooseUsDto dto);
    WhyChooseUs updateWhyChooseUs(@MappingTarget WhyChooseUs whyChooseUs, WhyChooseUsDto whyChooseUsDto);
    Missions missionsDtoToPojo(MissionsDto dto);
    Values valuesDtoToPojo(ValuesDto dto);
    ManageTeam manageTeamDtoToPojo(ManageTeamDto manageTeamDto);
    ContactCard contactCardDtoToPojo(ContactCardDto dto);
    KsmCard ksmCardDtoToPojo(KsmCardDto dto);
}
