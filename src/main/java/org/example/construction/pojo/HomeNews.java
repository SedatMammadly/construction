package org.example.construction.pojo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeNews {
    String slug;
    String title;
    String paragraph;
    List<String> images;
}
