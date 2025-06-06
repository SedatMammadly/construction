package org.example.construction.mapper;

import org.example.construction.dto.*;
import org.example.construction.model.Contact;
import org.example.construction.model.Values;
import org.example.construction.pojo.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PojoMapper {
    About aboutDtoToPojo(AboutDto dto);
    List<WhyChooseUs> whyChooseUsDtoListToPojoList(List<WhyChooseUsDto> dto);
    Missions missionsDtoToPojo(MissionsDto dto);
    Values valuesDtoToPojo(ValuesDto dto);
    ManageTeam manageTeamDtoToPojo(ManageTeamDto manageTeamDto);
    ContactCard contactCardDtoToPojo(ContactCardDto dto);
    KsmCard ksmCardDtoToPojo(KsmCardDto dto);
}
