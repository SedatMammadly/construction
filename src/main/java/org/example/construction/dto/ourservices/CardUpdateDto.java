package org.example.construction.dto.ourservices;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardUpdateDto {
    String headCategory;
    String subCategory;
    String header;
    String description;
    ContentDto content;
    List<String> images;
}
