package org.example.construction.service;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ContactCardDto;
 import org.example.construction.model.Contact;
import org.example.construction.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final FileService fileService;

    public Contact getContactPage() {
        return contactRepository.findAll().getFirst();
    }

        public Contact addContact(ContactCardDto contactDto, MultipartFile icon) throws IOException {
        Contact contact = new Contact();
        contact.setTitle(contactDto.getTitle());
        contact.setDesciption(contactDto.getDescription());
        contact.setIcon(fileService.uploadFile(icon));
        return contactRepository.save(contact);
    }

    public Contact updateContact(int id, ContactCardDto contactDto, MultipartFile icon) throws IOException {
        Contact contact = contactRepository.findById(id).orElseThrow();

        contact.setTitle(contactDto.getTitle());
        contact.setDesciption(contactDto.getDescription());

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
}
