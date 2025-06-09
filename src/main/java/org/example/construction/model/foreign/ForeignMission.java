package org.example.construction.model.foreign;

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
public class ForeignMission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String icon;
    String header;
    String description;
    @JdbcTypeCode(SqlTypes.JSON)
    Content content;
    String slug;
}
