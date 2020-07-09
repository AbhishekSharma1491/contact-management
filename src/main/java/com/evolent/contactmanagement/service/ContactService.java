package com.evolent.contactmanagement.service;

import com.evolent.contactmanagement.exception.ContactNotFoundException;
import com.evolent.contactmanagement.model.Contact;
import com.evolent.contactmanagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        List<Contact> result = contactRepository.findAll();
        return (result.size() > 0) ? result : new ArrayList<Contact>();
    }

    public Contact getContactById(Long id) throws ContactNotFoundException {
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isPresent())
            return contact.get();
        else
            throw new ContactNotFoundException("No contact found");
    }

    public Contact createOrUpdateContact(Contact contact) {
        if(contact.getId()==null)
            return contactRepository.save(contact);
        else {
            Optional<Contact> oldContact = contactRepository.findById(contact.getId());
            if(oldContact.isPresent()) {
                Contact newContact = oldContact.get();
                newContact.setEmail(contact.getEmail());
                newContact.setFirstName(contact.getFirstName());
                newContact.setLastName(contact.getLastName());
                newContact.setPhoneNumber(contact.getPhoneNumber());
                newContact.setStatus(contact.getStatus());
                newContact = contactRepository.save(newContact);
                return newContact;
            } else{
                return contactRepository.save(contact);
            }
        }
    }

    public void deleteContactById(Long id) throws ContactNotFoundException {
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isPresent())
            contactRepository.deleteById(id);
        else
            throw new ContactNotFoundException("No contact found for given id");
    }
}
