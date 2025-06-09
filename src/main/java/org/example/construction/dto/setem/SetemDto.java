package org.example.construction.dto.setem;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetemDto {
    String header;
    String description;
    String content;
}
