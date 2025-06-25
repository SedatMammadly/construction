package org.example.construction.dto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactMessageDto {
    String fullName;
    String email;
    String phone;
    String topic;
    String address;
    String message;
}
