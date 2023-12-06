package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactTest {
    private Contact contact;

    /**
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     * @param testInfo - to pull the display name off each test to display.
     */
    @BeforeEach
    void testFormattingPrintBefore(TestInfo testInfo) {
        String displayDate = testInfo.getDisplayName();
        int totalLength = 85; // Adjust the total length as needed
        int paddingLength = (totalLength - displayDate.length() - 2) / 2;
        int extraPadding = (totalLength - displayDate.length() - 2) % 2;

        String padding = "*".repeat(paddingLength);
        String padding2 = "=".repeat(totalLength);
        String extraPaddingStr = (extraPadding == 1) ? "*": "";

        System.out.println(padding2);
        System.out.printf("%s %s %s%s\n\n", padding, displayDate, padding, extraPaddingStr);
    }

    /**
     * sets up initial contact to check values against
     */
    @BeforeEach
    void setUp() {
        // Set up a new Contact object before each test
        contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
    }

    /**
     * After test Formatting for viewing pleasure!
     */
    @AfterEach
    void testFormattingPrintAfter() {
        int totalLength = 85; // Adjust the total length as needed
        String padding2 = "=".repeat(totalLength);
        System.out.println(padding2 + "\n\n");
    }

    /**
     * Retrieves ID of current Contact.
     */
    @Test
    @Order(1)
    @DisplayName("Retrieve Contact Id:")
    void testGetContactID() {
        assertEquals("1", contact.getContactID());
        System.out.println(contact.getContactID());
    }

    /**
     * Retrieves First Name of current Contact.
     */
    @Test
    @Order(2)
    @DisplayName("Retrieve First Name:")
    void testGetFirstName() {
        assertEquals("John", contact.getFirstName());
        System.out.println(contact.getFirstName());
    }

    /**
     * Retrieves Last Name of current Contact.
     */
    @Test
    @Order(3)
    @DisplayName("Retrieve Last Name:")
    void testGetLastName() {
        assertEquals("Doe", contact.getLastName());
        System.out.println(contact.getLastName());
    }

    /**
     * Retrieves Phone Number of current Contact.
     */
    @Test
    @Order(4)
    @DisplayName("Retrieve Phone Number:")
    void testGetPhoneNumber() {
        assertEquals("1234567890", contact.getPhoneNumber());
        System.out.println(contact.getPhoneNumber());
    }

    /**
     * Retrieves Address of current Contact.
     */
    @Test
    @Order(5)
    @DisplayName("Retrieve Address:")
    void testGetAddress() {
        assertEquals("123 Main St", contact.getAddress());
        System.out.println(contact.getAddress());
    }

    /**
     * Tests that the values of current contact are displayed.
     */
    @Test
    @Order(6)
    @DisplayName("Display Values of Current Contact:")
    void testDisplayValues() {
        // You can redirect System.out to capture the output for testing
        // For simplicity, let's just test if it runs without errors
        assertDoesNotThrow(() -> contact.displayValues());
    }

    /**
     * Checks each value to ensure null cannot pass.
     */
    @Test
    @Order(7)
    @DisplayName("Constructor Null Check: ID, First Name, Last Name, Phone Number, Address & Fail:")
    void testConstructorNullCheck() {
        // Test that the constructor throws NullPointerException for null values
        NullPointerException idNull = assertThrows(NullPointerException.class, () -> {
            new Contact(null, "First Name", "Last Name", "5555555555", "Some Address");
        }, "Illegal Argument was expected");
        assertEquals("Contact ID cannot be null!", idNull.getMessage());
        System.out.println(idNull.getMessage());

        NullPointerException firstNameNull = assertThrows(NullPointerException.class, () -> {
            new Contact("2", null, "Last Name", "5555555555", "Some Address");
        }, "Illegal Argument was expected");
        assertEquals("First Name cannot be null!", firstNameNull.getMessage());
        System.out.println(firstNameNull.getMessage());

        NullPointerException lastNameNull = assertThrows(NullPointerException.class, () -> {
            new Contact("2", "First Name", null, "5555555555", "Some Address");;
        }, "Illegal Argument was expected");
        assertEquals("Last Name cannot be null!", lastNameNull.getMessage());
        System.out.println(lastNameNull.getMessage());

        NullPointerException phoneNumberNull = assertThrows(NullPointerException.class, () -> {
            new Contact("2", "First Name", "Last Name", null, "Some Address");;
        }, "Illegal Argument was expected");
        assertEquals("Phone Number cannot be null!", phoneNumberNull.getMessage());
        System.out.println(phoneNumberNull.getMessage());

        NullPointerException addressNull = assertThrows(NullPointerException.class, () -> {
            new Contact("2", "First Name", "Last Name", "5555555555", null);;
        }, "Illegal Argument was expected");
        assertEquals("Address cannot be null!", addressNull.getMessage());
        System.out.println(addressNull.getMessage());

    }

    /**
     * Checks each value to ensure length is not exceeded.
     */
    @Test
    @Order(8)
    @DisplayName("Constructor Length Check: ID, Name, Description & Fail:")
    void testConstructorLengthCheck() {
        // Test that the constructor throws IllegalArgumentException for values exceeding length limits
        IllegalArgumentException idLong = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123456789011", "Some Name", "Last Name", "5555555555", "Some Place");
        }, "Illegal Argument was expected");
        assertEquals("Contact ID cannot be longer than 10!", idLong.getMessage());
        System.out.println(idLong.getMessage());

        IllegalArgumentException firstNameLong = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "aseriopiy1iokjhgpiuio", "Last Name", "5555555555", "Some Place");
        }, "Illegal Argument was expected");
        assertEquals("First Name cannot be longer than 10!", firstNameLong.getMessage());
        System.out.println(firstNameLong.getMessage());

        IllegalArgumentException lastNameLong = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "Some Name", "dfasdfasdfa", "5555555555", "Some Place" );
        }, "Illegal Argument was expected");
        assertEquals("Last Name cannot be longer than 10!", lastNameLong.getMessage());
        System.out.println(lastNameLong.getMessage());

        IllegalArgumentException phoneNumberLong = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1324567890", "Some Name", "Last Name", "55555555555", "Some Place");
        }, "Illegal Argument was expected");
        assertEquals("Phone Number cannot be longer than 10!", phoneNumberLong.getMessage());
        System.out.println(phoneNumberLong.getMessage());

        IllegalArgumentException addressLong = assertThrows(IllegalArgumentException.class, () -> {
            new Contact("132456789111", "Some Name", "Last Name", "5555555555", "asdfasdfasdfa1sdfaasdfasdfasdfasdfasdfasdfasdfasd33");
        }, "Illegal Argument was expected");
        assertEquals("Contact ID cannot be longer than 10!", addressLong.getMessage());
        System.out.println(addressLong.getMessage());
    }
}
