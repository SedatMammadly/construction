package org.example.construction.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.construction.pojo.About;
import org.example.construction.pojo.HomeNews;
import org.example.construction.pojo.HomeProjects;
import org.example.construction.pojo.WhyChooseUs;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @JdbcTypeCode(SqlTypes.JSON)
    About about;
    @JdbcTypeCode(SqlTypes.JSON)
    List<HomeProjects> projects = new ArrayList<>();
    @JdbcTypeCode(SqlTypes.JSON)
    List<WhyChooseUs> whyChooseUs;
    @JdbcTypeCode(SqlTypes.JSON)
    List<HomeNews> news;
}
