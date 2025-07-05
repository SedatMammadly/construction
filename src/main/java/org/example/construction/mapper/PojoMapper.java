package org.example.construction.mapper;

import org.example.construction.dto.*;
import org.example.construction.model.*;
import org.example.construction.pojo.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PojoMapper {
    Carousel carouselDtoToPojo(CarouselDto dto);
    WhyChooseUs whyChooseUsDtoToPojo(WhyChooseUsDto dto);
    WhyChooseUs updateWhyChooseUs(@MappingTarget WhyChooseUs whyChooseUs, WhyChooseUsDto whyChooseUsDto);
    void updateCarousel(@MappingTarget Carousel carousel, CarouselDto carouselDto);
    ContactMessage contactMessageDtoToEntity(ContactMessageDto contactMessageDto);
    void updateKsmFromDto(@MappingTarget Ksm ksm, KsmUpdateDto ksmUpdateDto);
}
