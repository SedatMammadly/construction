package org.example.construction.repository.service;

import org.example.construction.model.service.HeadCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeadCategoryRepository extends JpaRepository<HeadCategory,Long> {
    Optional<HeadCategory> findByName(String name);

    Optional<HeadCategory> findBySlug(String slug);
}
