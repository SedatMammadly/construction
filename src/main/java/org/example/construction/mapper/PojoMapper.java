package org.example.construction.mapper;

import org.example.construction.dto.*;
import org.example.construction.model.About;
import org.example.construction.model.Carousel;
import org.example.construction.model.ContactMessage;
import org.example.construction.model.WhyChooseUs;
import org.example.construction.pojo.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PojoMapper {
    About aboutDtoToPojo(AboutDto dto);
    Carousel carouselDtoToPojo(CarouselDto dto);
    WhyChooseUs whyChooseUsDtoToPojo(WhyChooseUsDto dto);
    WhyChooseUs updateWhyChooseUs(@MappingTarget WhyChooseUs whyChooseUs, WhyChooseUsDto whyChooseUsDto);
    ContactCard contactCardDtoToPojo(ContactCardDto dto);
    KsmCard ksmCardDtoToPojo(KsmCardDto dto);
    void updateCarousel(@MappingTarget Carousel carousel, CarouselDto carouselDto);
    ContactMessage contactMessageDtoToEntity(ContactMessageDto contactMessageDto);
}
