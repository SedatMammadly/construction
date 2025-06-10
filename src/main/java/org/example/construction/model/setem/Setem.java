package org.example.construction.model.setem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Setem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String header;
    String description;
    List<String> images;
    String icon;
    String content;
}
