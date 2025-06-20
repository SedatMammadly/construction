package org.example.construction.model.about;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Values {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String title;
    String icon;
    @Column(columnDefinition = "TEXT")
    String paragraph;
}
