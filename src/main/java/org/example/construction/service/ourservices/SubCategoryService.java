package org.example.construction.service.ourservices;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ourservices.SubCategoryDto;
import org.example.construction.model.service.Card;
import org.example.construction.model.service.SubCategory;
import org.example.construction.repository.service.SubCategoryRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;


    // Create
    public SubCategory createSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryDto.getName());
        subCategory.setHeadCategory(subCategoryDto.getHeadCategory());
        subCategory.setSlug(SlugUtil.toSlug(subCategoryDto.getName()));
        subCategory.setHeadCategorySlug(SlugUtil.toSlug(subCategoryDto.getHeadCategory()));
        return subCategoryRepository.save(subCategory);
    }

    // Read - Single
    public Optional<SubCategory> getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id);
    }

    // Read - All
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }


    // Delete
    public void deleteSubCategory(Long id) {
        subCategoryRepository.deleteById(id);
    }

    public Optional<SubCategory> getSubCategoryBySlug(String slug) {
        return subCategoryRepository.findBySlug(slug);
    }

    public List<SubCategory> getSubCategoriesByHeadSlug(String headSlug) {
        return subCategoryRepository.findByHeadCategorySlug(headSlug);
    }
}