package org.example.construction.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class KsmUpdateDto {
    String title;
    String description;
    String paragraph;
    List<String> images;
}
