package org.example.construction.repository.service;

import org.example.construction.model.service.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
 

    Optional<SubCategory> findBySlug(String slug);

    List<SubCategory> findByHeadCategorySlug(String headSlug);
}
