package org.example.construction.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpecialUpdateDto {
    String name;
    String content;
    List<String>images;
}
