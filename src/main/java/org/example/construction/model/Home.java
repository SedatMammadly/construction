package org.example.construction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
