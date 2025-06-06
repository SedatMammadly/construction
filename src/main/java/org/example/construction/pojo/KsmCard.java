package org.example.construction.pojo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KsmCard {
    String icon;
    String title;
    String description;
    List<String>images;
}
