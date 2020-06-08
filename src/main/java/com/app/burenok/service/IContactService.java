package com.app.burenok.service;

import com.app.burenok.exception.ContactIdNotFoundException;
import com.app.burenok.model.Contact;

import java.util.List;

public interface IContactService {


    List<Contact> getAllContacts();
    Contact getContactById(long id) throws ContactIdNotFoundException;
    Contact createContact(Contact contact);
    Contact updateContact(long id, Contact contact) throws ContactIdNotFoundException;
    void deleteContact( long id) throws ContactIdNotFoundException;
}
