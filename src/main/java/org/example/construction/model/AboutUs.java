package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;

import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.ManageTeam;
import org.example.construction.pojo.Missions;
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
    Integer id;
    @JdbcTypeCode(SqlTypes.JSON)
    List<Missions> missions;
    @Column(columnDefinition = "TEXT")
    String visions;
    @JdbcTypeCode(SqlTypes.JSON)
    List<Values> values;
    @ElementCollection
    List<String>history;
    @JdbcTypeCode(SqlTypes.JSON)
    List<ManageTeam> manageTeams;
    @Column(columnDefinition = "TEXT")
    String managementStructure;
    @ElementCollection
    List<String> certificates = new ArrayList<>();
}
