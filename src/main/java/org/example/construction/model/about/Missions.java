package org.example.construction.model.about;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Missions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String icon;
    String title;
    String description;
}
