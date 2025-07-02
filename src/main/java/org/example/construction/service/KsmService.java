package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.KsmCardDto;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Ksm;
import org.example.construction.repository.KsmRepository;
import org.example.construction.util.SlugUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KsmService {
    private final KsmRepository ksmRepository;
    private final FileService fileService;
    private final PojoMapper pojoMapper;


    public Ksm addKsmCards(KsmCardDto ksmCardDto, List<MultipartFile> images, MultipartFile icon) throws IOException {
        Ksm ksm = new Ksm();
        ksm.setTitle(ksmCardDto.getTitle());
        ksm.setSlug(SlugUtil.toSlug(ksmCardDto.getTitle()));
        ksm.setDescription(ksmCardDto.getDescription());
        ksm.setParagraph(ksmCardDto.getParagraph());
        ksm.setIcon(fileService.uploadFile(icon));
        ksm.setImages(fileService.uploadFiles(images));
        return ksmRepository.save(ksm);
    }

    public Ksm updateKsmCard(int index, KsmCardDto ksmCardDto, MultipartFile icon, List<MultipartFile> images) throws IOException {
        Ksm ksm = ksmRepository.findById(index).get();
        ksm.setTitle(ksmCardDto.getTitle());
        ksm.setDescription(ksmCardDto.getDescription());
        ksm.setParagraph(ksmCardDto.getParagraph());
        ksm.setSlug(SlugUtil.toSlug(ksmCardDto.getTitle()));

        fileService.deleteFile(ksm.getIcon());
        fileService.deleteFiles(ksm.getImages());
        ksm.setIcon(fileService.uploadFile(icon));
        ksm.setImages(fileService.uploadFiles(images));

        return ksmRepository.save(ksm);
    }

    public void deleteKsmCard(int id) {
        Optional<Ksm> ksmCards = ksmRepository.findById(id);

        List<String> imagesList = ksmCards.get().getImages();
        if (imagesList != null) {
            for (int i = 0; i < imagesList.size(); i++) {
                String imageFile = imagesList.get(i);
                fileService.deleteFile(imageFile);
            }
        }
        fileService.deleteFile(ksmCards.get().getIcon());
        ksmRepository.deleteById(id);

    }

}
