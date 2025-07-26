package com.devopsdemo.tutorial.addressbook.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testGettersAndSetters() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhone("1234567890");
        contact.setEmail("john.doe@example.com");

        assertEquals(1L, contact.getId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("john.doe@example.com", contact.getEmail());
    }
}
