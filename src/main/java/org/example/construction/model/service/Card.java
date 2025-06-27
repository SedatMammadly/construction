package org.example.construction.model.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String headCategory;
    String subCategory;
    String headCategorySlug;
    String subCategorySlug;
    String slug;
    String header;
    String description;
    @JdbcTypeCode(SqlTypes.JSON)
    Content content;
}
