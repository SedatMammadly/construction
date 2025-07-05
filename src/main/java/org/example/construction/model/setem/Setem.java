package org.example.construction.model.setem;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Setem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String header;
    String description;
    String icon;
    @Column(columnDefinition = "TEXT")
    String content;
}
