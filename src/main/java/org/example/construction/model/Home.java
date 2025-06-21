package org.example.construction.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Home {
    About about;
    List<Projects> projects = new ArrayList<>();
    List<WhyChooseUs> whyChooseUs;
    List<News> news;
}
