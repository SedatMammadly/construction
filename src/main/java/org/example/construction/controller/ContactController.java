package org.example.construction.controller;

import lombok.RequiredArgsConstructor;
import org.example.construction.dto.ContactCardDto;
import org.example.construction.model.Contact;
import org.example.construction.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<Contact> getContactPage() {
        return ResponseEntity.ok(contactService.getContactPage());
    }

    @PostMapping
    public ResponseEntity<Contact> addContactCards(@RequestPart(name = "request") List<ContactCardDto> ContactCardDtos,
                                   @RequestPart(required = false) List<MultipartFile> icons) throws IOException {
        Contact contact = contactService.addContactCards(ContactCardDtos, icons);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    @PutMapping("/update/contactCard/{index}")
    public ResponseEntity<Contact> updateContactCard(@PathVariable int index,@RequestPart(name = "request") ContactCardDto ContactCardDto,
                                                     @RequestPart(required = false) MultipartFile icon) throws IOException {
        Contact contact = contactService.updateContactCard(index, ContactCardDto, icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    @DeleteMapping("/delete/contactCard/{index}")
    public ResponseEntity<Void> deleteContactCard(@PathVariable int index){
        contactService.deleteContactCard(index);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
