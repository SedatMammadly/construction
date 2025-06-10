package org.example.construction.model.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Content {
    String contentWrite;
    String mainImage;
    List<String> images;
}
