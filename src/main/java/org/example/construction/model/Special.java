package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Special {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    @Column(columnDefinition = "TEXT")
    String content;
    String slug;
    @JdbcTypeCode(SqlTypes.JSON)
    List<String> images;
}
