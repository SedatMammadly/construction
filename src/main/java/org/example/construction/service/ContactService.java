package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ContactCardDto;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Contact;
import org.example.construction.pojo.ContactCard;
import org.example.construction.repository.ContactRepository;
import org.example.construction.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final PojoMapper pojoMapper;
    private final FileService fileService;

    public Contact getContactPage() {
        return contactRepository.findAll().getFirst();
    }

    public Contact addContactCards(List<ContactCardDto> contactCardDtos, List<MultipartFile> icons) {
        Contact contact = new Contact();
        List<ContactCard> contactCards = new ArrayList<>();
        for (ContactCardDto contactCardDto : contactCardDtos) {
            ContactCard contactCard = pojoMapper.contactCardDtoToPojo(contactCardDto);
            contactCards.add(contactCard);
        }
        if (icons != null) {
            for (int i = 0; i < icons.size(); i++) {
                String iconFile = fileService.uploadFile(icons.get(i), "image");
                contactCards.get(i).setIcon(iconFile);
            }
        }
        contact.setContactCards(contactCards);
        return contactRepository.save(contact);
    }

    public Contact updateContactCard(int index, ContactCardDto contactCardDto, MultipartFile icon) {
        Contact contact = contactRepository.findAll().getFirst();
        List<ContactCard> contactCards = contact.getContactCards();
        contactCards.set(index, pojoMapper.contactCardDtoToPojo(contactCardDto));
        if (icon != null) {
            String iconFile = fileService.uploadFile(icon, "image");
            contactCards.get(index).setIcon(iconFile);
        }
        contact.setContactCards(contactCards);
        return contactRepository.save(contact);
    }

    public void deleteContactCard(int index) {
        Contact contact = contactRepository.findAll().getFirst();
        List<ContactCard> contactCards = contact.getContactCards();
        contactCards.remove(index);
        fileService.removeFile(contactCards.get(index).getIcon());
    }
}
