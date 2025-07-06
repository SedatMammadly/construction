package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.KsmDto;
import org.example.construction.dto.KsmUpdateDto;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Ksm;
import org.example.construction.repository.KsmRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KsmService {
    private final KsmRepository ksmRepository;
    private final FileService fileService;
    private final PojoMapper pojoMapper;


    public Ksm addKsmCards(KsmDto ksmDto, List<MultipartFile> images, MultipartFile icon) throws IOException {
        Ksm ksm = new Ksm();
        ksm.setTitle(ksmDto.getTitle());
        ksm.setSlug(SlugUtil.toSlug(ksmDto.getTitle()));
        ksm.setDescription(ksmDto.getDescription());
        ksm.setParagraph(ksmDto.getParagraph());
        if (icon != null && !icon.isEmpty()) {
            ksm.setIcon(fileService.uploadFile(icon));
        }
        ksm.setImages(fileService.uploadFiles(images));
        return ksmRepository.save(ksm);
    }

    public Ksm updateKsmCard(int index, KsmUpdateDto ksmUpdateDto, MultipartFile icon, List<MultipartFile> images) throws IOException {
        Ksm ksm = ksmRepository.findById(index)
                .orElseThrow(() -> new RuntimeException("Ksm not found"));

        ksm.setSlug(SlugUtil.toSlug(ksmUpdateDto.getTitle()));
        List<String> currentImages = new ArrayList<>(ksm.getImages());
        List<String> toRemoveImages = new ArrayList<>();
        for (String image : currentImages) {
            if (!ksmUpdateDto.getImages().contains(image)) {
                toRemoveImages.add(image);
            }
        }

        fileService.deleteFiles(toRemoveImages);
        pojoMapper.updateKsmFromDto(ksm, ksmUpdateDto);
        List<String> updatedImages = new ArrayList<>(ksmUpdateDto.getImages());

        if (images != null && !images.isEmpty()) {
            List<String> uploaded = fileService.uploadFiles(images);
            updatedImages.addAll(uploaded);
        }

        ksm.setImages(updatedImages);

        if (icon != null && !icon.isEmpty()) {
            fileService.deleteFile(ksm.getIcon());
            ksm.setIcon(fileService.uploadFile(icon));
        }

        return ksmRepository.save(ksm);
    }


    public void deleteKsmCard(int id) {
        Optional<Ksm> ksmCards = ksmRepository.findById(id);

        List<String> imagesList = ksmCards.get().getImages();
        if (imagesList != null) {
            fileService.deleteFiles(imagesList);
        }
        fileService.deleteFile(ksmCards.get().getIcon());
        ksmRepository.deleteById(id);
    }

}
