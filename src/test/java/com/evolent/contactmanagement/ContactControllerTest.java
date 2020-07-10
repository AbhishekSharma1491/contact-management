package com.evolent.contactmanagement;

import com.evolent.contactmanagement.exception.ContactNotFoundException;
import com.evolent.contactmanagement.model.Contact;
import com.evolent.contactmanagement.model.Status;
import com.evolent.contactmanagement.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactService contactService;

    @Test
    public void testGetAllContacts() throws Exception {
        Mockito.when(contactService.getAllContacts()).thenReturn(getSampleContacts());
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateContact() throws Exception {
        Mockito.when(contactService.createOrUpdateContact(getSampleContacts().get(0))).thenReturn(getSampleContacts().get(0));
        mvc.perform(post("/createContact", getSampleContacts().get(0)))
                .andExpect(status().is(302));
    }

    @Test
    public void testDeleteContactById() throws ContactNotFoundException, Exception {
        Mockito.doNothing().when(contactService).deleteContactById(Mockito.any());
        mvc.perform(get("/deleteContact/1"))
                .andExpect(status().is(302));
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
}
