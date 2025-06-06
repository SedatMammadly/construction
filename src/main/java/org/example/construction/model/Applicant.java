package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String fullName;
    String contactNumber;
    String email;
    String position;
    @Column(columnDefinition = "TEXT")
    String motivation;
    String cvFile;
}
