package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.SubContent;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    String slug;
    String name;
    LocalDate constructDate;
    String orderOwner;
    @Column(columnDefinition = "TEXT")
    String content;
    @ElementCollection
    List<String> images;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;
}
