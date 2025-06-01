package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KsmPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String icon;
    String title;
    String paragraph;
}
