package org.jeremiahboothe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    private Contact contact;

    @BeforeEach
    void setUp() {
        // Set up a new Contact object before each test
        contact = new Contact(1, "John", "Doe", "1234567890", "123 Main St");
    }

    @Test
    void testGetContactID() {
        assertEquals(1, contact.getContactID());
    }

    @Test
    void testGetLastName() {
        assertEquals("Doe", contact.getLastName());
    }

    @Test
    void testGetFirstName() {
        assertEquals("John", contact.getFirstName());
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("1234567890", contact.getPhoneNumber());
    }

    @Test
    void testGetAddress() {
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    void testDisplayValues() {
        // You can redirect System.out to capture the output for testing
        // For simplicity, let's just test if it runs without errors
        assertDoesNotThrow(() -> contact.displayValues());
    }

    @Test
    void testConstructorNullCheck() {
        // Test that the constructor throws NullPointerException for null values
        assertThrows(NullPointerException.class, () -> new Contact(1, null, "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void testConstructorLengthCheck() {
        // Test that the constructor throws IllegalArgumentException for values exceeding length limits
        assertThrows(IllegalArgumentException.class, () -> new Contact(1, "Johnnnnnnnnnn", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void testUpdateContact() {
        // Test updating an existing contact
        Contact updatedContact = new Contact(contact, null, null, "9876543210", "456 Second St");

        assertEquals(contact.getContactID(), updatedContact.getContactID());
        assertEquals(contact.getFirstName(), updatedContact.getFirstName());
        assertEquals(contact.getLastName(), updatedContact.getLastName());
        assertEquals("9876543210", updatedContact.getPhoneNumber());
        assertEquals("456 Second St", updatedContact.getAddress());
    }
}
