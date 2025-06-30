package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ContactCardDto;
import org.example.construction.dto.ContactMessageDto;
import org.example.construction.model.Contact;
import org.example.construction.model.ContactMessage;
import org.example.construction.repository.ContactRepository;
import org.example.construction.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
private final ContactRepository contactRepository;
    @GetMapping("/getAll")
    public ResponseEntity<List<Contact>> getContacts() {
        return ResponseEntity.ok(contactService.getContacts());
    }

    @PostMapping("/add")
    public ResponseEntity<Contact> addContactCards(@RequestPart(name = "request") ContactCardDto ContactCardDto,
                                                   @RequestPart(required = true) MultipartFile icon) throws IOException {
        Contact contact = contactService.addContact(ContactCardDto, icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Contact>> getByIdContact(@PathVariable int id) {
        return ResponseEntity.ok(contactRepository.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContactCard(@PathVariable int id, @RequestPart(name = "request") ContactCardDto ContactCardDto,
                                                     @RequestPart(required = false) MultipartFile icon) throws IOException {
        Contact contact = contactService.updateContact(id, ContactCardDto, icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContactCard(@PathVariable int id) {
        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getAll/contactMessages")
    public ResponseEntity<List<ContactMessage>> getAllContactMessages() {
        return ResponseEntity.ok(contactService.getAllContactMessages());
    }

    @PostMapping("/apply")
    public ResponseEntity<String>sendContactMessage(@RequestBody ContactMessageDto contactMessageDto){
        return ResponseEntity.ok(contactService.sendContactMessage(contactMessageDto));
    }

    @DeleteMapping("/delete/contactMessages/{id}")
    public ResponseEntity<Void> deleteContactMessage(@PathVariable Integer id) {
        contactService.deleteContactMessage(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
