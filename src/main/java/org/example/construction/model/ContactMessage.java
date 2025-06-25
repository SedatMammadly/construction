package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String fullName;
    String email;
    String phone;
    String topic;
    String address;
    @Column(columnDefinition = "TEXT")
    String message;
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;
}
