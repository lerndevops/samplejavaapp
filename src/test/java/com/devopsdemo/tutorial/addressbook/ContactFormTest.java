package com.devopsdemo.tutorial.addressbook;

import com.devopsdemo.tutorial.addressbook.backend.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactFormTest {

    private ContactForm contactForm;

    @BeforeEach
    void setUp() {
        contactForm = new ContactForm();
    }

    @Test
    void testEditContact() {
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setEmail("john.doe@example.com");

        contactForm.edit(contact);

        assertTrue(contactForm.isVisible());
        assertEquals("John", contactForm.firstName.getValue());
        assertEquals("Doe", contactForm.lastName.getValue());
        assertEquals("john.doe@example.com", contactForm.email.getValue());
    }

    @Test
    void testEditNullContact() {
        contactForm.edit(null);

        assertFalse(contactForm.isVisible());
    }

    @Test
    void testSaveContact() {
        Contact contact = new Contact();
        contact.setFirstName("Jane");
        contact.setLastName("Smith");
        contact.setEmail("jane.smith@example.com");

        contactForm.edit(contact);
        contactForm.save.click();

        // Assuming save logic updates the UI or backend, validate the expected behavior here.
        assertTrue(contactForm.isVisible());
    }

    @Test
    void testCancelContact() {
        Contact contact = new Contact();
        contact.setFirstName("Alice");
        contact.setLastName("Brown");

        contactForm.edit(contact);
        contactForm.cancel.click();

        assertFalse(contactForm.isVisible());
    }
}
