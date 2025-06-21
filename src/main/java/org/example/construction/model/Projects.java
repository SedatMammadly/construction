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

import java.time.LocalDateTime;
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
    String image;
    @JdbcTypeCode(SqlTypes.JSON)
    List<SubContent> subContents;
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;
}
