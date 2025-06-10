package org.example.construction.dto.ourservices;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubCategoryDto {
    String name;
    String headCategory;
}