package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ourservices.CardDto;
import org.example.construction.dto.ourservices.ContentDto;
import org.example.construction.dto.ourservices.HeadCategoryDto;
import org.example.construction.dto.ourservices.SubCategoryDto;
import org.example.construction.model.service.Card;
import org.example.construction.model.service.Content;
import org.example.construction.model.service.HeadCategory;
import org.example.construction.model.service.SubCategory;
import org.example.construction.repository.service.CardRepository;
import org.example.construction.repository.service.SubCategoryRepository;
import org.example.construction.service.ourservices.CardService;
import org.example.construction.service.ourservices.HeadCategoryService;
import org.example.construction.service.ourservices.SubCategoryService;
import org.example.construction.util.SlugUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class OurServicesController {

    private final CardService cardService;
    private final HeadCategoryService headCategoryService;
    private final SubCategoryService subCategoryService;
    private final CardRepository cardRepository;
    private final SubCategoryRepository subCategoryRepository;

    /// /////////////////// CARD //////////////////////

    @PostMapping("/card/create")
    public Card createCard(@RequestPart CardDto cardDto, MultipartFile mainImage, List<MultipartFile> images) throws IOException {
        return cardService.createCard(cardDto, mainImage, images);
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        return cardService.getCardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/card/all")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @PutMapping("/card/{id}")
    public Card updateCard(@PathVariable Long id, @RequestPart CardDto cardDto, MultipartFile mainImage, List<MultipartFile> images) throws IOException {
        return cardService.updateCard(id, cardDto, mainImage, images);
    }

    @DeleteMapping("/card/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @GetMapping("/card/head-category/{headCategorySlug}")
    public List<Card> getCardsByHeadCategorySlug(@PathVariable String headCategorySlug) {
        return cardService.getCardsByHeadCategorySlug(headCategorySlug);
    }

    @GetMapping("/card/sub-category/{subCategorySlug}")
    public List<Card> getCardsBySubCategorySlug(@PathVariable String subCategorySlug) {
        return cardService.getCardsBySubCategorySlug(subCategorySlug);
    }

    /// /////////////////// HEAD CATEGORY //////////////////////

    @PostMapping("/head-category/create")
    public HeadCategory createHeadCategory(@RequestBody HeadCategoryDto headCategoryDto) {
        return headCategoryService.createHeadCategory(headCategoryDto);
    }

    @GetMapping("/head-category/{id}")
    public ResponseEntity<HeadCategory> getHeadCategoryById(@PathVariable Long id) {
        return headCategoryService.getHeadCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/head-category/all")
    public List<HeadCategory> getAllHeadCategories() {
        return headCategoryService.getAllHeadCategories();
    }

    @PutMapping("/head-category/{id}")
    public HeadCategory updateHeadCategory(@PathVariable Long id, @RequestBody HeadCategoryDto headCategoryDto) {
        return headCategoryService.updateHeadCategory(id, headCategoryDto);
    }

    @DeleteMapping("/head-category/{id}")
    public void deleteHeadCategory(@PathVariable Long id) {
        headCategoryService.deleteHeadCategory(id);
    }

    @GetMapping("/head-category/slug/{slug}")
    public ResponseEntity<HeadCategory> getHeadCategoryBySlug(@PathVariable String slug) {
        return headCategoryService.getHeadCategoryBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /// /////////////////// SUB CATEGORY //////////////////////

    @PostMapping("/sub-category/create")
    public SubCategory createSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        return subCategoryService.createSubCategory(subCategoryDto);
    }

    @GetMapping("/sub-category/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        return subCategoryService.getSubCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sub-category/all")
    public List<SubCategory> getAllSubCategories() {
        return subCategoryService.getAllSubCategories();
    }

    @PutMapping("/sub-category/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody String newName) {
        Optional<SubCategory> subCategoryOpt = subCategoryRepository.findById(id);

        if (subCategoryOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SubCategory subCategory = subCategoryOpt.get();
        String oldName = subCategory.getName();

        // İlgili kartları bul ve güncelle
        List<Card> cards = cardRepository.findBySubCategory(oldName);
        for (Card card : cards) {
            card.setSubCategory(newName);
            cardRepository.save(card);
        }

        // SubCategory ismini ve slug'ını güncelle
        subCategory.setName(newName);
        subCategory.setSlug(SlugUtil.toSlug(newName));
        subCategoryRepository.save(subCategory);

        return ResponseEntity.ok(subCategory);
    }


    @DeleteMapping("/sub-category/{id}")
    public void deleteSubCategory(@PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);
    }

    @GetMapping("/sub-category/head/{headSlug}")
    public List<SubCategory> getSubCategoriesByHeadSlug(@PathVariable String headSlug) {
        return subCategoryService.getSubCategoriesByHeadSlug(headSlug);
    }

    @GetMapping("/sub-category/slug/{slug}")
    public ResponseEntity<SubCategory> getSubCategoryBySlug(@PathVariable String slug) {
        return subCategoryService.getSubCategoryBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
