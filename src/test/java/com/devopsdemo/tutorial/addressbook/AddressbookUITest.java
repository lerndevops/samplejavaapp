package com.devopsdemo.tutorial.addressbook;

import com.devopsdemo.tutorial.addressbook.backend.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressbookUITest {

    private AddressbookUI addressbookUI;

    @BeforeEach
    void setUp() {
        addressbookUI = new AddressbookUI();
    }

    @Test
    void testConfigureComponents() {
        assertNotNull(addressbookUI);
        assertNotNull(addressbookUI.newContact);
        assertNotNull(addressbookUI.filter);
    }

    @Test
    void testNewContactButton() {
        addressbookUI.newContact.click();
        assertTrue(addressbookUI.contactForm.isVisible());
    }

    @Test
    void testFilterContacts() {
        addressbookUI.filter.setValue("John");
        // Add assertions to verify filtered results
    }
}
