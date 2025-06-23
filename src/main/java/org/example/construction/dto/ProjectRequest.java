package org.example.construction.dto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.SubContent;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {
    String name;
    LocalDate contructDate;
    String orderOwner;
    String content;
}
