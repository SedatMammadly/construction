package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ContactCardDto;
import org.example.construction.dto.ContactMessageDto;
import org.example.construction.mapper.PojoMapper;
import org.example.construction.model.Contact;
import org.example.construction.model.ContactMessage;
import org.example.construction.repository.ContactMessageRepository;
import org.example.construction.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final FileService fileService;
    private final PojoMapper pojoMapper;

    private final ContactMessageRepository contactMessageRepository;
    public Contact getContactPage() {
        return contactRepository.findAll().getFirst();
    }

    public Contact addContact(ContactCardDto contactDto, MultipartFile icon) throws IOException {
        Contact contact = new Contact();
        contact.setTitle(contactDto.getTitle());
        contact.setDescription(contactDto.getDescription());
        contact.setIcon(fileService.uploadFile(icon));
        return contactRepository.save(contact);
    }

    public Contact updateContact(int id, ContactCardDto contactDto, MultipartFile icon) throws IOException {
        Contact contact = contactRepository.findById(id).orElseThrow();

        contact.setTitle(contactDto.getTitle());
        contact.setDescription(contactDto.getDescription());

        if (contact.getIcon() != null) {
            fileService.removeFile(contact.getIcon());
        }

        contact.setIcon(fileService.uploadFile(icon));

        return contactRepository.save(contact);
    }

    public void deleteContact(int id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();

            if (contact.getIcon() != null) {
                fileService.removeFile(contact.getIcon());
            }

            contactRepository.deleteById(id);
        }
    }
    public String sendContactMessage(ContactMessageDto contactMessageDto) {
        ContactMessage contactMessage = pojoMapper.contactMessageDtoToEntity(contactMessageDto);
        contactMessageRepository.save(contactMessage);
        return "Message send successfully";
    }

    public List<ContactMessage> getAllContactMessages() {
        return contactMessageRepository.findAll();
    }
}
