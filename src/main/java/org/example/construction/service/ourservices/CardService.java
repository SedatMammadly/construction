package org.example.construction.service.ourservices;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ourservices.CardDto;
import org.example.construction.dto.ourservices.CardUpdateDto;
import org.example.construction.dto.ourservices.ContentDto;
import org.example.construction.repository.service.CardRepository;
import org.example.construction.service.FileService;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;


import org.example.construction.model.service.Card;
import org.example.construction.model.service.Content;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final FileService fileService;

    // Create
    public Card createCard(CardDto cardDto, MultipartFile mainImage, List<MultipartFile> images) throws IOException {
        Card card = new Card();
        Content content = new Content();
        content.setContentWrite(cardDto.getContent().getContentWrite());
        content.setMainImage(fileService.uploadFile(mainImage));
        content.setImages(fileService.uploadFiles(images));
        card.setContent(content);
        card.setSlug(SlugUtil.toSlug(cardDto.getHeader()));
        card.setHeader(cardDto.getHeader());
        card.setSubCategory(cardDto.getSubCategory());
        card.setHeadCategory(cardDto.getHeadCategory());
        card.setDescription(cardDto.getDescription());
        card.setHeadCategorySlug(SlugUtil.toSlug(cardDto.getHeadCategory()));
        card.setSubCategorySlug(SlugUtil.toSlug(cardDto.getSubCategory()));
        return cardRepository.save(card);
    }

    // Read - Single
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    // Read - All
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    // Update
    public Card updateCard(Long id, CardUpdateDto updatedCard, MultipartFile mainImage, List<MultipartFile> images) throws IOException {
        Optional<Card> existCard = cardRepository.findById(id);

        if (existCard.isPresent()) {
            Card card = existCard.get();

            // Kart temel bilgileri
            card.setHeader(updatedCard.getHeader());
            card.setDescription(updatedCard.getDescription());
            card.setSubCategory(updatedCard.getSubCategory());
            card.setSlug(SlugUtil.toSlug(updatedCard.getSubCategory()));
            card.setHeadCategory(updatedCard.getHeadCategory());

            // İçerik bilgisi
            Content content = new Content();
            content.setContentWrite(updatedCard.getContent().getContentWrite());

            // Ana görseli kaydet
            content.setMainImage(fileService.uploadFile(mainImage));

            // Yeni gelen görselleri yükle
            List<String> uploadedImages = fileService.uploadFiles(images);

            // Eski görselleri al (DTO'dan)
            List<String> existingImages = updatedCard.getImages();

            // Hepsini birleştir
            List<String> allImages = new ArrayList<>();
            if (existingImages != null) allImages.addAll(existingImages);
            if (uploadedImages != null) allImages.addAll(uploadedImages);

            content.setImages(allImages);
            card.setContent(content);

            return cardRepository.save(card);
        }

        return null;
    }


    // Delete
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    public List<Card> getCardsBySubCategorySlug(String subCategorySlug) {
        return cardRepository.findBySubCategorySlug(subCategorySlug);
    }

    public List<Card> getCardsByHeadCategorySlug(String headCategorySlug) {
        return cardRepository.findByHeadCategorySlug(headCategorySlug);
    }
}