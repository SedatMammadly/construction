package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String slug;
    String title;
    @Column(columnDefinition = "TEXT")
    String content;
    String image;
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;
}
