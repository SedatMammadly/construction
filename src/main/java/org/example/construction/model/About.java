package org.example.construction.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.AboutCard;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String paragraph;
    String image;
    @JdbcTypeCode(SqlTypes.JSON)
    List<AboutCard> aboutCards;
}
