package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Carousel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String header;
    String text;
    String image;
}
