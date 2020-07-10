package com.evolent.contactmanagement;

import com.evolent.contactmanagement.exception.ContactNotFoundException;
import com.evolent.contactmanagement.model.Contact;
import com.evolent.contactmanagement.model.Status;
import com.evolent.contactmanagement.repository.ContactRepository;
import com.evolent.contactmanagement.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class ContactServiceTest {

    @InjectMocks
    ContactService contactService;

    @Mock
    ContactRepository contactRepository;

    @Test
    public void testGetAllContacts() {
        Mockito.when(contactRepository.findAll()).thenReturn(getSampleContacts());
        List<Contact> contacts = contactService.getAllContacts();
        assertEquals(1, contacts.size());
    }

    @Test
    public void testGetContactById() throws ContactNotFoundException {
        Contact contact = getSampleContacts().get(0);
        Mockito.when(contactRepository.findById(any())).thenReturn(Optional.of(contact));
        Contact response = contactService.getContactById(1l);
        assertEquals(1, response.getId());
    }

    @Test
    public void testCreateOrUpdateContact() {
        Contact contact = getSampleContacts().get(0);
        Mockito.when(contactRepository.findById(any())).thenReturn(Optional.of(contact));
        Mockito.when(contactRepository.save(any())).thenReturn(mapContact(contact));
        Contact response = contactService.createOrUpdateContact(contact);
        assertEquals(response.getStatus(), Status.INACTIVE);
    }

    @Test
    public void testDeleteContactById_Success() {
        Contact contact = getSampleContacts().get(0);
        Mockito.when(contactRepository.findById(any())).thenReturn(Optional.of(contact));
        assertDoesNotThrow(() -> contactService.deleteContactById(anyLong()));
    }

    @Test
    public void testDeleteContactById_ContactNotFoundException() {
        Contact contact = getSampleContacts().get(0);
        Mockito.when(contactRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ContactNotFoundException.class, () -> contactService.deleteContactById(anyLong()));
    }

    private List<Contact> getSampleContacts() {
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setFirstName("tom");
        contact.setLastName("carry");
        contact.setStatus(Status.ACTIVE);
        contact.setPhoneNumber("9876543562");
        contacts.add(contact);
        return contacts;
    }

    private Contact mapContact(Contact oldContact) {
        Contact newContact = new Contact();
        newContact.setId(oldContact.getId());
        newContact.setStatus(Status.INACTIVE);
        newContact.setPhoneNumber(oldContact.getPhoneNumber());
        newContact.setFirstName(oldContact.getFirstName());
        newContact.setLastName(oldContact.getLastName());
        newContact.setEmail(oldContact.getEmail());
        return newContact;
    }
}
