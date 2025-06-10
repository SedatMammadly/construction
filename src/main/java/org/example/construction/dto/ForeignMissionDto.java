package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.construction.dto.ourservices.ContentDto;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForeignMissionDto {
    String header;
    String description;
    ContentDto content;

}
