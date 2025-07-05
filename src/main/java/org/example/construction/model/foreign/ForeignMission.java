package org.example.construction.model.foreign;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForeignMission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String icon;
    String header;
    String description;
    @Column(columnDefinition = "TEXT")
    String content;
    List<String>images;
    String slug;
}
