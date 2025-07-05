package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {
    String name;
    String orderOwner;
    String content;
    LocalDate constructDate;
}
