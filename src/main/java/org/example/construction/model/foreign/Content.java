package org.example.construction.model.foreign;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Content {
    String contentWrite;
    List<String> images;
}
