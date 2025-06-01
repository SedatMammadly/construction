package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.SubContent;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectUpdateDto {
    String name;
    String slug;
    List<SubContent> subContents;
}
