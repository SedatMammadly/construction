package org.example.construction.pojo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class About {
    String paragraph;
    String image;
    List<AboutCard> aboutCards;
}
