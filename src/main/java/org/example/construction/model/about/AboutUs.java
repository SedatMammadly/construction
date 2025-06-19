package org.example.construction.model.about;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;

import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    List<Missions> missions;

    @JdbcTypeCode(SqlTypes.JSON)
    Vision vision;

    @JdbcTypeCode(SqlTypes.JSON)
    List<Values> values;

    @JdbcTypeCode(SqlTypes.JSON)
    History history;

    @JdbcTypeCode(SqlTypes.JSON)
    List<ManageTeam> manageTeams;

    @JdbcTypeCode(SqlTypes.JSON)
    ManagementStructure managementStructure;

    @JdbcTypeCode(SqlTypes.JSON)
    List<Certificate> certificates;
}
