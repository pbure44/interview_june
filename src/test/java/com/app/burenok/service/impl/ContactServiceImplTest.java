package com.app.burenok.service.impl;

import com.app.burenok.exception.ContactIdNotFoundException;
import com.app.burenok.model.Contact;
import com.app.burenok.repository.IContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContactServiceImplTest {

    @Mock
    private IContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private static final Contact CONTACT_ONE = Contact.builder().id(150).name("Contact_One").surname("Surn_One").birthday(new Date(2000, 11, 21))
            .login("Login_One").password("Passw_One").aboutContact("AboutContact_One").address("Address_One").build();
    private static final Contact CONTACT_TWO = Contact.builder().id(151).name("Contact_Two").surname("Surn_Two").birthday(new Date(2005, 5, 2))
            .login("Login_Two").password("Passw_Two").aboutContact("AboutContact_Two").address("Address_Two").build();
    private static final Contact CONTACT_THREE = Contact.builder().id(152).name("Contact_Three").surname("Surn_Three").birthday(new Date(2010, 3, 11))
            .login("Login_One").password("Passw_Three").aboutContact("AboutContact_Three").address("Address_Three").build();

    @Test
    public void testGetAllContactsSuccess() {
        List<Contact> contactList = new LinkedList<>();
        contactList.add(CONTACT_ONE);
        contactList.add(CONTACT_TWO);
        contactList.add(CONTACT_THREE);
        when(contactRepository.findAll()).thenAnswer((Answer<List<Contact>>) invocationOnMock -> contactList);
        List<Contact> testList = contactService.getAllContacts();
        assertEquals(contactList, testList);
    }

    @Test
    public void testGetContactByIdSuccess() throws ContactIdNotFoundException {
        when(contactRepository.findById(CONTACT_ONE.getId())).thenAnswer((Answer<Optional<Contact>>) invocationOnMock -> Optional.of(CONTACT_ONE));
        assertEquals(CONTACT_ONE, contactService.getContactById(CONTACT_ONE.getId()));
    }

    @Test
    public void createContact() {
        when(contactRepository.save(any(Contact.class)))
                .thenAnswer((Answer<Contact>) invocation -> {
                    Contact contact = (Contact) invocation.getArguments()[0];
                    return contact;
                });
        assertEquals(CONTACT_ONE, contactService.createContact(CONTACT_ONE));
    }

    @Test
    public void whenUpdatingItemItShouldReturnTheSavedItem() throws ContactIdNotFoundException {
        when(contactRepository.findById(CONTACT_ONE.getId())).thenAnswer((Answer<Optional<Contact>>) invocationOnMock -> Optional.of(CONTACT_ONE));
        given(contactRepository.save(CONTACT_ONE)).willReturn(CONTACT_ONE);
        assertEquals(contactService.updateContact(CONTACT_ONE.getId(), CONTACT_ONE), CONTACT_ONE);
    }

    @Test
    public void whenUpdatingItemItShouldUseTheGivenID() throws ContactIdNotFoundException {
        ArgumentCaptor<Contact> anyItem = ArgumentCaptor.forClass(Contact.class);
        given(contactRepository.findById(CONTACT_ONE.getId())).willReturn(Optional.of(CONTACT_ONE));
        contactService.updateContact(CONTACT_ONE.getId(), CONTACT_TWO);
        verify(contactRepository).save(anyItem.capture());
        assertThat(anyItem.getValue().getId()).isEqualTo(CONTACT_ONE.getId());
    }

    @Test()
    public void whenDeletingAnItemItShouldUseTheRepository() throws ContactIdNotFoundException {
        given(contactRepository.findById(CONTACT_ONE.getId())).willReturn(Optional.of(CONTACT_ONE));
        contactService.deleteContact(CONTACT_ONE.getId());
        verify(contactRepository).deleteById(CONTACT_ONE.getId());
    }

}

