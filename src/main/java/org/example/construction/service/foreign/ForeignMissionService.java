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

        if (foreignMissionUpdateDto.getHeader() != null)
            foreignMission.setHeader(foreignMissionUpdateDto.getHeader());

        if (foreignMissionUpdateDto.getDescription() != null)
            foreignMission.setDescription(foreignMissionUpdateDto.getDescription());

        if (foreignMissionUpdateDto.getContent() != null)
            foreignMission.setContent(foreignMissionUpdateDto.getContent());

        // Eski resimleri al
        List<String> existingImages = foreignMission.getImages() != null
                ? new ArrayList<>(foreignMission.getImages())
                : new ArrayList<>();

        // Yeni yüklenen resimler varsa onları da yükle ve ekle
        if (images != null && !images.isEmpty()) {
            List<String> uploadedImages = fileService.uploadFiles(images);
            existingImages.addAll(uploadedImages);
        }

        // İçeriğe tüm resimleri set et
        foreignMission.setImages(existingImages);

        // İkon yüklendiyse değiştir
        if (icon != null && !icon.isEmpty())
            foreignMission.setIcon(fileService.uploadFile(icon));

        // Slug güncelle
        foreignMission.setSlug(SlugUtil.toSlug(foreignMission.getHeader()));

        return foreignRepository.save(foreignMission);
    }


}
