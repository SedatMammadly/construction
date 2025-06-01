package org.example.construction.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.AboutCard;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutDto {
    String paragraph;
    List<AboutCard> aboutCards;
}
