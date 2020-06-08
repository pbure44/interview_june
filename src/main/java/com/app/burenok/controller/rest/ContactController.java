package com.app.burenok.controller.rest;

import com.app.burenok.exception.ContactIdNotFoundException;
import com.app.burenok.model.Contact;
import com.app.burenok.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ContactController {

    private final ContactServiceImpl contactService;

    @Autowired
    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    /**
     * Retrieve all contacts available in system
     * @return all contacts available in system
     */
    @GetMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getAllContacts() {
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }

    /**
     * Searching contact by given Id
     * @param id ID of target contact
     * @return contact with given Id
     * @throws ContactIdNotFoundException when contact not found
     */
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") long id) throws ContactIdNotFoundException {
        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }

    /**
     * Save given contact to database
     * @param contact contact to save
     * @return contact that was created
     */
    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.CREATED);
    }

    /**
     * Update contact with the following ID
     *
     * @param id   ID of contact to update
     * @param data data for update
     * @return contact that was updated
     * @throws ContactIdNotFoundException when contact not found
     */
    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact data) throws ContactIdNotFoundException {
        return new ResponseEntity<>(contactService.updateContact(id, data), HttpStatus.OK);
    }

    /**
     * Delete contact with the following ID
     *
     * @param id ID of contact to delete
     * @throws ContactIdNotFoundException when contact not found
     */
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") long id) throws ContactIdNotFoundException {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
