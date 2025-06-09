package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.KsmCardDto;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Ksm;
import org.example.construction.pojo.KsmCard;
import org.example.construction.repository.KsmRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KsmService {
    private final KsmRepository ksmRepository;
    private final FileService fileService;
    private final PojoMapper pojoMapper;

    public Ksm getKsmPage() {
        return ksmRepository.findAll().getFirst();
    }

    public Ksm addKsmCards(List<KsmCardDto> ksmCardDtos, List<MultipartFile> icons) throws IOException {
        Ksm ksm = new Ksm();
        List<KsmCard> ksmCards = new ArrayList<>();
        for (KsmCardDto ksmCardDto : ksmCardDtos) {
            KsmCard contactCard = pojoMapper.ksmCardDtoToPojo(ksmCardDto);
            ksmCards.add(contactCard);
        }
        if (icons != null) {
            for (int i = 0; i < icons.size(); i++) {
                String iconFile = fileService.uploadFile(icons.get(i));
                ksmCards.get(i).setIcon(iconFile);
            }
        }
        ksm.setKsmCards(ksmCards);
        return ksmRepository.save(ksm);
    }

    public Ksm updateKsmCard(int index, KsmCardDto ksmCardDto, MultipartFile icon,List<MultipartFile> images) throws IOException {
        Ksm ksm = ksmRepository.findAll().getFirst();
        List<KsmCard> ksmCards = ksm.getKsmCards();
        List<String>imagesList = new ArrayList<>();
        ksmCards.set(index, pojoMapper.ksmCardDtoToPojo(ksmCardDto));
        if (icon != null) {
            String iconFile = fileService.uploadFile(icon);
            ksmCards.get(index).setIcon(iconFile);
        }
        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                String imageFile = fileService.uploadFile(images.get(i));
                imagesList.add(imageFile);
            }
        }
        ksmCards.get(index).setImages(imagesList);
        ksm.setKsmCards(ksmCards);
        return ksmRepository.save(ksm);
    }

    public void deleteKsmCard(int index) {
        Ksm ksm = ksmRepository.findAll().getFirst();
        List<KsmCard> ksmCards = ksm.getKsmCards();
        List<String>imagesList = ksmCards.get(index).getImages();
        if (imagesList != null) {
            for (int i = 0; i < imagesList.size(); i++) {
                String imageFile = imagesList.get(i);
                fileService.removeFile(imageFile);
            }
        }
        ksmCards.remove(index);
        fileService.removeFile(ksmCards.get(index).getIcon());
    }

}
