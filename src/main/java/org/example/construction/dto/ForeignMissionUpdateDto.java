package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.construction.dto.ourservices.ContentDto;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForeignMissionUpdateDto {
    String header;
    String description;
    List<String>images;
    String content;

}
