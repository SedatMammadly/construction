package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUpdateDto {
    String name;
    String orderOwner;
    String content;
    LocalDate constructDate;
    List<String> images;
}
