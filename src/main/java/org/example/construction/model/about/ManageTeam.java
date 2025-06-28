package org.example.construction.model.about;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManageTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String image;
    String title;
    String work;
    @Column(columnDefinition = "TEXT")
    String paragraph;
}
