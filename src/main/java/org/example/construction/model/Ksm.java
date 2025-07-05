package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.KsmCard;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ksm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String icon;
    String title;
    String slug;
    String description;
    @Column(columnDefinition = "TEXT")
    String paragraph;
    @ElementCollection
    List<String> images;

}
