package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeRequest {
    AboutDto about;
    List<WhyChooseUsDto> whyChooseUs;
}

