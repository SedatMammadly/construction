package org.example.construction.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SetemUpdateDto {
    String header;
    String description;
    String content;
    List<String> images;
}
