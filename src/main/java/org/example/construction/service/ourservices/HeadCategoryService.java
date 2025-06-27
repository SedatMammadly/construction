package org.example.construction.service.ourservices;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ourservices.HeadCategoryDto;
import org.example.construction.model.service.Card;
import org.example.construction.model.service.HeadCategory;
import org.example.construction.repository.service.CardRepository;
import org.example.construction.repository.service.HeadCategoryRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeadCategoryService {

    private final HeadCategoryRepository headCategoryRepository;
    private final CardRepository cardRepository;


    // Create
    public HeadCategory createHeadCategory(HeadCategoryDto headCategoryDto) {
        HeadCategory headCategory = new HeadCategory();
        headCategory.setName(headCategoryDto.getName());
        headCategory.setDescription(headCategoryDto.getDescription());
        headCategory.setSlug(SlugUtil.toSlug(headCategoryDto.getName()));
        return headCategoryRepository.save(headCategory);
    }

    // Read - Single
    public Optional<HeadCategory> getHeadCategoryById(Long id) {
        return headCategoryRepository.findById(id);
    }

    // Read - All
    public List<HeadCategory> getAllHeadCategories() {
        return headCategoryRepository.findAll();
    }

    // Update
    public HeadCategory updateHeadCategory(Long id, HeadCategoryDto headCategoryDto) {
        HeadCategory headCategory = headCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeadCategory not found with id: " + id));
        List<Card> cards = cardRepository.findByHeadCategory(headCategory.getName());
        for (Card card : cards) {
            card.setHeadCategory(headCategoryDto.getName());
            cardRepository.save(card);
        }
        headCategory.setDescription(headCategoryDto.getDescription());
        headCategory.setName(headCategoryDto.getName());
        headCategory.setSlug(SlugUtil.toSlug(headCategoryDto.getName()));

        return headCategoryRepository.save(headCategory);

    }

    // Delete
    public void deleteHeadCategory(Long id) {
        headCategoryRepository.deleteById(id);
    }

    // Additional custom methods
    public Optional<HeadCategory> getHeadCategoryBySlug(String slug) {
        return headCategoryRepository.findBySlug(slug);
    }
}