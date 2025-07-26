package com.devopsdemo.tutorial.addressbook.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService = ContactService.createDemoService();
    }

    @Test
    void testCreateDemoService() {
        assertNotNull(contactService);
        assertTrue(contactService.findAll(null).size() > 0);
    }

    @Test
    void testFindAll() {
        List<Contact> contacts = contactService.findAll(null);
        assertNotNull(contacts);
        assertFalse(contacts.isEmpty());
    }

    @Test
    void testFindAllWithFilter() {
        List<Contact> contacts = contactService.findAll("Peter");
        assertNotNull(contacts);
        assertTrue(contacts.stream().allMatch(contact -> contact.toString().toLowerCase().contains("peter")));
    }

    @Test
    void testSaveContact() {
        Contact contact = new Contact();
        contact.setFirstName("Test");
        contact.setLastName("User");
        contact.setEmail("test.user@example.com");
        contact.setPhone("+1234567890");
        contact.setBirthDate(LocalDate.of(1990, 1, 1));

        contactService.save(contact);

        List<Contact> contacts = contactService.findAll("Test");
        assertTrue(contacts.contains(contact));
    }

    @Test
    void testDeleteContact() {
        Contact contact = new Contact();
        contact.setFirstName("Delete");
        contact.setLastName("Me");
        contactService.save(contact);

        contactService.delete(contact);

        List<Contact> contacts = contactService.findAll("Delete");
        assertFalse(contacts.contains(contact));
    }

    @Test
    void testCount() {
        long count = contactService.count();
        assertTrue(count > 0);
    }
}
