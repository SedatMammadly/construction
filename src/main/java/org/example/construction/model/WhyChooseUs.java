package org.example.construction.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WhyChooseUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String icon;
    String title;
    String paragraph;
}
