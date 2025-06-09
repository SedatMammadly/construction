package org.example.construction.service.ourservices;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ourservices.CardDto;
import org.example.construction.dto.ourservices.ContentDto;
import org.example.construction.repository.service.CardRepository;
import org.example.construction.service.FileService;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;


import org.example.construction.model.service.Card;
import org.example.construction.model.service.Content;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Card updateCard(Long id, CardDto updatedCard, MultipartFile mainImage, List<MultipartFile> images) throws IOException {
        Optional<Card> existCard = cardRepository.findById(id);
        if (existCard.isPresent()) {
            existCard.get().setHeader(updatedCard.getHeader());
            existCard.get().setDescription(updatedCard.getDescription());
            existCard.get().setSubCategory(updatedCard.getSubCategory());
            existCard.get().setHeadCategory(updatedCard.getHeadCategory());
            Content content = new Content();
            content.setContentWrite(updatedCard.getContent().getContentWrite());
            content.setMainImage(fileService.uploadFile(mainImage));
            content.setImages(fileService.uploadFiles(images));
            existCard.get().setContent(content);
        }
        return cardRepository.save(existCard.get());

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