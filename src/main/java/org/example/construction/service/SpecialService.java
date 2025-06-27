package org.example.construction.service;


import lombok.RequiredArgsConstructor;
import org.example.construction.dto.SpecialDto;
import org.example.construction.model.Special;
import org.example.construction.repository.SpecialRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialService {

    private final SpecialRepository specialRepository;
    private final FileService fileService;

    public Special createSpecial(SpecialDto specialDto, List<MultipartFile> images) throws IOException {
        Special special = new Special();
        special.setName(specialDto.getName());
        special.setContent(specialDto.getContent());
        special.setSlug(SlugUtil.toSlug(specialDto.getName()));
        special.setImages(fileService.uploadFiles(images));

        return specialRepository.save(special);
    }

    public List<Special> getAllSpecials() {
        return specialRepository.findAll();
    }

    public Optional<Special> getSpecialById(Long id) {
        return specialRepository.findById(id);
    }

    public Special updateSpecial(Long id, SpecialDto specialDto, List<MultipartFile> newImages) throws IOException {
        Special special = specialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Special not found"));
        fileService.deleteFiles(special.getImages());
        special.setName(specialDto.getName());
        special.setContent(specialDto.getContent());
        special.setSlug(SlugUtil.toSlug(specialDto.getName()));

        if (newImages != null && !newImages.isEmpty()) {
            List<String> uploadedImages = fileService.uploadFiles(newImages);
            special.setImages(uploadedImages); // eski fotolar silinmiş sayılır
        }

        return specialRepository.save(special);
    }

    public void deleteSpecial(Long id) {
        if (!specialRepository.existsById(id)) {
            throw new RuntimeException("Special not found");
        }
        specialRepository.deleteById(id);
    }
}
