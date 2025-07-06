package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ourservices.*;
import org.example.construction.model.service.Card;
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

    @PostMapping("/card/add")
    public Card addCard(
            @RequestPart CardDto cardDto,
            @RequestPart(required = false) MultipartFile mainImage,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        return cardService.createCard(cardDto, mainImage, images);
    }

    @GetMapping("/card/getBySlug/{slug}")
    public ResponseEntity<Card> getCardBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(cardRepository.findBySlug(slug));
    }

    @GetMapping("/card/get/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        return cardService.getCardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/card/getAll")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @PutMapping("/card/update/{id}")
    public Card updateCard(
            @PathVariable Long id,
            @RequestPart CardUpdateDto cardDto,
            @RequestPart MultipartFile mainImage,
            @RequestPart(required = false) List<MultipartFile> images
    ) throws IOException {
        return cardService.updateCard(id, cardDto, mainImage, images);
    }

    @DeleteMapping("/card/delete/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @GetMapping("/card/getByHeadCategory/{headCategorySlug}")
    public List<Card> getCardsByHeadCategorySlug(@PathVariable String headCategorySlug) {
        return cardService.getCardsByHeadCategorySlug(headCategorySlug);
    }

    @GetMapping("/card/getBySubCategory/{subCategorySlug}")
    public List<Card> getCardsBySubCategorySlug(@PathVariable String subCategorySlug) {
        return cardService.getCardsBySubCategorySlug(subCategorySlug);
    }

    /// /////////////////// HEAD CATEGORY //////////////////////

    @PostMapping("/head-category/add")
    public HeadCategory addHeadCategory(@RequestBody HeadCategoryDto headCategoryDto) {
        return headCategoryService.createHeadCategory(headCategoryDto);
    }

    @GetMapping("/head-category/get/{id}")
    public ResponseEntity<HeadCategory> getHeadCategoryById(@PathVariable Long id) {
        return headCategoryService.getHeadCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/head-category/getAll")
    public List<HeadCategory> getAllHeadCategories() {
        return headCategoryService.getAllHeadCategories();
    }

    @PutMapping("/head-category/update/{id}")
    public HeadCategory updateHeadCategory(
            @PathVariable Long id,
            @RequestBody HeadCategoryDto headCategoryDto
    ) {
        return headCategoryService.updateHeadCategory(id, headCategoryDto);
    }

    @DeleteMapping("/head-category/delete/{id}")
    public void deleteHeadCategory(@PathVariable Long id) {
        headCategoryService.deleteHeadCategory(id);
    }

    @GetMapping("/head-category/getBySlug/{slug}")
    public ResponseEntity<HeadCategory> getHeadCategoryBySlug(@PathVariable String slug) {
        return headCategoryService.getHeadCategoryBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /// /////////////////// SUB CATEGORY //////////////////////

    @PostMapping("/sub-category/add")
    public SubCategory addSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        return subCategoryService.createSubCategory(subCategoryDto);
    }

    @GetMapping("/sub-category/get/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        return subCategoryService.getSubCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sub-category/getAll")
    public List<SubCategory> getAllSubCategories() {
        return subCategoryService.getAllSubCategories();
    }

    @PutMapping("/sub-category/update/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(
            @PathVariable Long id,
            @RequestBody SubCategoryDto subCategoryDto
    ) {
        Optional<SubCategory> subCategoryOpt = subCategoryRepository.findById(id);

        if (subCategoryOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SubCategory subCategory = subCategoryOpt.get();
        String oldName = subCategory.getName();

        // kartların subcategory isimlerini de güncelle
        List<Card> cards = cardRepository.findBySubCategory(oldName);
        for (Card card : cards) {
            card.setSubCategory(subCategoryDto.getName());
            card.setSubCategorySlug(SlugUtil.toSlug(subCategoryDto.getName()));
            card.setHeadCategory(subCategoryDto.getHeadCategory());
            card.setHeadCategorySlug(SlugUtil.toSlug(subCategoryDto.getHeadCategory()));
            cardRepository.save(card);
        }
        subCategory.setHeadCategory(subCategoryDto.getHeadCategory());
        subCategory.setName(subCategoryDto.getName());
        subCategory.setSlug(SlugUtil.toSlug(subCategoryDto.getName()));
        subCategory.setHeadCategorySlug(SlugUtil.toSlug(subCategoryDto.getHeadCategory()));
        subCategoryRepository.save(subCategory);

        return ResponseEntity.ok(subCategory);
    }

    @DeleteMapping("/sub-category/delete/{id}")
    public void deleteSubCategory(@PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);
    }

    @GetMapping("/sub-category/getByHeadSlug/{headSlug}")
    public List<SubCategory> getSubCategoriesByHeadSlug(@PathVariable String headSlug) {
        return subCategoryService.getSubCategoriesByHeadSlug(headSlug);
    }

    @GetMapping("/sub-category/getBySlug/{slug}")
    public ResponseEntity<SubCategory> getSubCategoryBySlug(@PathVariable String slug) {
        return subCategoryService.getSubCategoryBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
