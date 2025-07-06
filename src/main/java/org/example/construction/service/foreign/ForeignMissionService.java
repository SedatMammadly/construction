package org.example.construction.service.foreign;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ForeignMissionDto;
import org.example.construction.dto.ForeignMissionUpdateDto;
import org.example.construction.model.foreign.Content;
import org.example.construction.model.foreign.ForeignMission;
import org.example.construction.repository.foreign.ForeignRepository;
import org.example.construction.service.FileService;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForeignMissionService {
    private final FileService fileService;
    private final ForeignRepository foreignRepository;

    public ForeignMission add(ForeignMissionDto foreignMissionDto, List<MultipartFile> images, MultipartFile icon) throws IOException {
        ForeignMission foreignMission = new ForeignMission();

        foreignMission.setImages(fileService.uploadFiles(images));
        foreignMission.setIcon(fileService.uploadFile(icon));
        foreignMission.setContent(foreignMissionDto.getContent());
        foreignMission.setDescription(foreignMissionDto.getDescription());
        foreignMission.setHeader(foreignMissionDto.getHeader());
        foreignMission.setSlug(SlugUtil.toSlug(foreignMissionDto.getHeader()));
        return foreignRepository.save(foreignMission);
    }

    public ForeignMission update(Long id, ForeignMissionUpdateDto foreignMissionUpdateDto, List<MultipartFile> images, MultipartFile icon) throws IOException {
        ForeignMission foreignMission = foreignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foreign mission not found"));

        // Slug güncelle (header üzerinden)
        if (foreignMissionUpdateDto.getHeader() != null) {
            foreignMission.setHeader(foreignMissionUpdateDto.getHeader());
            foreignMission.setSlug(SlugUtil.toSlug(foreignMissionUpdateDto.getHeader()));
        }

        if (foreignMissionUpdateDto.getDescription() != null)
            foreignMission.setDescription(foreignMissionUpdateDto.getDescription());

        if (foreignMissionUpdateDto.getContent() != null)
            foreignMission.setContent(foreignMissionUpdateDto.getContent());

        // Eski resimleri al
        List<String> existingImages = foreignMission.getImages() != null
                ? new ArrayList<>(foreignMission.getImages())
                : new ArrayList<>();

        // Silinecek resimleri bul (DTO'da olmayanları sil)
        List<String> toRemove = new ArrayList<>();
        for (String oldImage : existingImages) {
            if (!foreignMissionUpdateDto.getImages().contains(oldImage)) {
                toRemove.add(oldImage);
            }
        }

        // Eski dosyaları sil
        fileService.deleteFiles(toRemove);

        // Yeni resim listesi = DTO'dan gelenler (korunacaklar)
        List<String> updatedImages = new ArrayList<>(foreignMissionUpdateDto.getImages());

        // Yeni yüklenen dosyaları ekle
        if (images != null && !images.isEmpty()) {
            updatedImages.addAll(fileService.uploadFiles(images));
        }

        // Güncellenmiş resim listesini set et
        foreignMission.setImages(updatedImages);

        // Icon yüklendiyse değiştir
        if (icon != null && !icon.isEmpty()) {
            foreignMission.setIcon(fileService.uploadFile(icon));
        }

        return foreignRepository.save(foreignMission);
    }



}
