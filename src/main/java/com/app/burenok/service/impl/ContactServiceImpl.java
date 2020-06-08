package com.app.burenok.service.impl;

import com.app.burenok.exception.ContactIdNotFoundException;
import com.app.burenok.model.Contact;
import com.app.burenok.repository.IContactRepository;
import com.app.burenok.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements IContactService {

    private IContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Retrieve all contacts available in system
     *
     * @return all contacts available in system
     */
    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Searching contact by given Id
     *
     * @param id ID of target contact
     * @return contact with given Id
     * @throws ContactIdNotFoundException when contact not found
     */
    @Override
    public Contact getContactById(long id) throws ContactIdNotFoundException {
        Optional<Contact> _contact = contactRepository.findById(id);
        if (_contact.isPresent()) {
            return _contact.get();
        } else {
            throw new ContactIdNotFoundException(id);
        }
    }

    /**
     * Save given contact to database
     *
     * @param contact contact to save
     * @return contact that was created
     */
    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Update contact with the following ID
     *
     * @param id   ID of contact to update
     * @param data data for update
     * @return contact that was updated
     * @throws ContactIdNotFoundException when contact not found
     */
    @Override
    public Contact updateContact(long id, Contact data) throws ContactIdNotFoundException {
        Optional<Contact> contactData = contactRepository.findById(id);
        if (contactData.isPresent()) {
            return contactRepository.save(contactData.get().copyFrom(data));
        } else {
            throw new ContactIdNotFoundException(id);
        }
    }

    /**
     * Delete contact with the following ID
     *
     * @param id ID of contact to delete
     * @throws ContactIdNotFoundException when contact not found
     */
    @Override
    public void deleteContact(long id) throws ContactIdNotFoundException {
        Optional<Contact> _contact = contactRepository.findById(id);
        if (_contact.isPresent()) {
            contactRepository.deleteById(id);
        } else {
            throw new ContactIdNotFoundException(id);
        }
    }
}
