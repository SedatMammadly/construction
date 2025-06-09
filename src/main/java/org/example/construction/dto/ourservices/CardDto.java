package org.example.construction.dto.ourservices;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.construction.model.service.Content;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardDto {
    String headCategory;
    String subCategory;
    String header;
    String description;
    ContentDto content;
}
