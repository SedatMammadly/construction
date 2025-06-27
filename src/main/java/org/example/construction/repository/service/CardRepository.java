package org.example.construction.repository.service;

import org.example.construction.model.service.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByHeadCategory(String headCategory);

    List<Card> findBySubCategory(String subCategory);

    List<Card> findBySubCategorySlug(String subCategorySlug);

    List<Card> findByHeadCategorySlug(String headCategorySlug);

    Card findBySlug(String slug);
}
