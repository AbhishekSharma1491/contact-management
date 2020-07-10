package com.evolent.contactmanagement.controller;

import com.evolent.contactmanagement.exception.ContactNotFoundException;
import com.evolent.contactmanagement.model.Contact;
import com.evolent.contactmanagement.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String getAllContacts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "list-contacts";
    }

    @PostMapping(path = "/createContact")
    public String createContact(@ModelAttribute Contact contact) {
        contactService.createOrUpdateContact(contact);
        return "redirect:/";
    }

    @RequestMapping(path = {"/editContact", "/editContact/{id}"})
    public String editContactById(Model model, @PathVariable("id") Optional<Long> id)
            throws ContactNotFoundException {
        if (id.isPresent()) {
            Contact contact = contactService.getContactById(id.get());
            model.addAttribute("contact", contact);
        } else {
            model.addAttribute("contact", new Contact());
        }
        return "add-update-contact";
    }

    @RequestMapping(path = "/deleteContact/{id}")
    public String deleteContactById(Model model, @PathVariable("id") Long id)
            throws ContactNotFoundException {
        contactService.deleteContactById(id);
        return "redirect:/";
    }
}
