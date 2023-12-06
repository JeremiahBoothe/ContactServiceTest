package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactServiceTest {
    private static ContactService contactService;

    /**
     * Initializes HashMap and ContactService Singleton for testing.
     */
    @BeforeAll
    static void setupBeforeAll() {
        contactService = ContactService.getInstance();
    }

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
     * BeforeEach test formatting, to make it a little bit more enjoyable to read!
     */
    @AfterEach
    void testFormattingPrintAfter() {
        int totalLength = 85; // Adjust the total length as needed
        String padding2 = "=".repeat(totalLength);
        System.out.println(padding2 + "\n\n");
    }

    /**
     * Parameterized test to run null check and length check, ensuring values that are too long or null are rejected.
     * @param firstName CSV First Names
     * @param lastName CSV Last Names
     * @param phoneNumber CSV Phone Numbers
     * @param address CSV Addresses
     */
    @Order(1)
    @CsvSource({
            ", Isabella,Anderson,4325555678,123 Oak St",
            "Rob@gl.com, ,Smith,4325551234,456 Pine Ln",
            "S@mail.com, Sophia,,4325559275,789 Elm Blvd",
            "Ms@mil.com, Mason,Clark, ,890 Cedar Rd",
            "Am@ail.com, Amelia,Cooper,4325558765,",
            "bla@Mars.com, Sophia,Clark,4325559275,789 Elm Blvd",
            "Loi@do.com, EmmaGenopolis,Evans,4325553456,567 Maple Dr",
            "Eli@La.com, Elijah,TurnerBakerson,4325556789,890 Cedar Rd",
            "efd@Mr.com, Emma,Miller,14325554321,456 Oak Ave",
            "RAb@gr.com, Olivia,Davis,4325553456,8000 Taumatawhakatangihangakoauauo",
            "bla@Mars.com, Sophia,Clark,4325559275,789 Elm Blvd",
            "Loi@do.com, Emma, Evans,4325553456,567 Maple Dr",
            "Eli@La.com, Elijah,Turner,4325556789,890 Cedar Rd",
            "efd@Mr.com, Emma,Miller,4325554321,456 Oak Ave",
            "efd@Mr.com, Emma,Miller,4325554321,456 Oak Ave"})
    @ParameterizedTest
    @DisplayName("Test Exceptions for Null and Length")
    void testAddContactAndGetContactById(String contactId,
                                         String firstName,
                                         String lastName,
                                         String phoneNumber,
                                         String address) {
        try {
            contactService.addContact(
                    contactService.createNewContactToAddToMap(contactId,
                    firstName,
                    lastName,
                    phoneNumber,
                    address));

            assertNotNull(contactService.getContactById(contactId), "The added Contact Should not be Null");
            contactService.displayValues(contactId);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests print allContacts to make sure it's not throwing an error, the contacts currently in the map are printed to console.
     */
    @Test
    @Order(2)
    @DisplayName("Prints Parameterized Test Map Values:")
    void testPrintAllMapAppointmentsFromParam() {
        assertDoesNotThrow(() -> {
            contactService.printAllContacts();
        });
    }

    /**
     * Checks retrieval of each value, by ID, from the map.
     */
    @Test
    @Order(3)
    @DisplayName("Checks retrieval of each value, by Id, from the map.")
    void testGetValuesById() {
        String contactId = "99";
        String firstName = "Lennry";
        String lastName = "Balthazor";
        String phoneNumber = "4325559275";
        String address = "333 Happy Place";

        assertDoesNotThrow(() -> {
            contactService.addContact(contactService
                    .createNewContactToAddToMap(
                            contactId,
                            "Lennry",
                            "Balthazor",
                            "4325559275",
                            "333 Happy Place"));
            assertAll(
                    () -> assertNotNull(contactService.getContactById(contactId)),
                    () -> assertEquals(firstName, contactService.getFirstName(contactId)),
                    () -> assertEquals(lastName, contactService.getLastName(contactId)),
                    () -> assertEquals(phoneNumber, contactService.getPhoneNumber(contactId)),
                    () -> assertEquals(address, contactService.getAddress(contactId))
            );
            System.out.println(contactService.getFirstName(contactId));
            System.out.println(contactService.getLastName(contactId));
            System.out.println(contactService.getPhoneNumber(contactId));
            System.out.println(contactService.getAddress(contactId));

        });
        assertThrows(NullPointerException.class, () -> {
            assertNull(contactService.getContactById("777").getAddress());
        });
    }

    /**
     * Tests and verifies deleting contact and not being able to again delete a contact after it has been deleted.
     */
    @Test
    @Order(4)
    @DisplayName("Test Deletion if exists, Fails if null:")
    void testDeleteContact() {
        assertDoesNotThrow(() -> {
            contactService.addContact(contactService
                    .createNewContactToAddToMap("1",
                            "asdf",
                            "asdfgh",
                            "340531",
                            "234345"));
            contactService.deleteContact("1");
            assertNull(contactService.getContactById("1"));
        });

        try {
            contactService.deleteContact("1");

        } catch (NullPointerException e) {
            assertTrue(true,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Steps through creating a contact and updating the Contact, individually updating each Value.
     */
    @Test
    @Order(6)
    @DisplayName("Update Contact by parameter:")
    void testUpdateContactFromMap() {
        String contactId = "53354";

        // Found out variables must be final or reference objects for use with assertAll, which I was using for grouping and readability.
        var reference = new Object() {
            String firstName = "Bob";
            String lastName = "Barker";
            String phoneNumber = "9999999999";
            String address = "The Price is Right!";

        };

        contactService.addContact(contactService
                .createNewContactToAddToMap(contactId,
                        reference.firstName,
                        reference.lastName,
                        reference.phoneNumber,
                        reference.address));

        assertAll(
                () -> assertNotNull(contactService.getContactById(contactId)),
                () -> assertEquals(reference.firstName, contactService.getFirstName(contactId)),
                () -> assertEquals(reference.lastName, contactService.getLastName(contactId)),
                () -> assertEquals(reference.phoneNumber, contactService.getPhoneNumber(contactId)),
                () -> assertEquals(reference.address, contactService.getAddress(contactId))

        );
        contactService.getContactById(contactId).displayValues();
        System.out.println("\n");

        reference.firstName = "UPDATED";
        contactService.updateFirstName(contactId, reference.firstName);
        assertAll(
                () -> assertNotNull(contactService.getContactById(contactId)),
                () -> assertEquals(reference.firstName, contactService.getFirstName(contactId)),
                () -> assertEquals(reference.lastName, contactService.getLastName(contactId)),
                () -> assertEquals(reference.phoneNumber, contactService.getPhoneNumber(contactId)),
                () -> assertEquals(reference.address, contactService.getAddress(contactId))
        );
        contactService.getContactById(contactId).displayValues();
        System.out.println("\n");

        reference.lastName = "UPDATED";
        contactService.updateLastName(contactId, reference.lastName);
        assertAll(
                () -> assertNotNull(contactService.getContactById(contactId)),
                () -> assertEquals(reference.firstName, contactService.getFirstName(contactId)),
                () -> assertEquals(reference.lastName, contactService.getLastName(contactId)),
                () -> assertEquals(reference.phoneNumber, contactService.getPhoneNumber(contactId)),
                () -> assertEquals(reference.address, contactService.getAddress(contactId))
        );
        contactService.getContactById(contactId).displayValues();
        System.out.println("\n");

        reference.phoneNumber = "UPDATED";
        contactService.updatePhoneNumber(contactId, reference.phoneNumber);
        assertAll(
                () -> assertNotNull(contactService.getContactById(contactId)),
                () -> assertEquals(reference.firstName, contactService.getFirstName(contactId)),
                () -> assertEquals(reference.lastName, contactService.getLastName(contactId)),
                () -> assertEquals(reference.phoneNumber, contactService.getPhoneNumber(contactId)),
                () -> assertEquals(reference.address, contactService.getAddress(contactId))
        );
        contactService.getContactById(contactId).displayValues();
        System.out.println("\n");

        reference.address = "UPDATED";
        contactService.updateAddress(contactId, reference.address);
        assertAll(
                () -> assertNotNull(contactService.getContactById(contactId)),
                () -> assertEquals(reference.firstName, contactService.getFirstName(contactId)),
                () -> assertEquals(reference.lastName, contactService.getLastName(contactId)),
                () -> assertEquals(reference.phoneNumber, contactService.getPhoneNumber(contactId)),
                () -> assertEquals(reference.address, contactService.getAddress(contactId))
        );
        contactService.getContactById(contactId).displayValues();
        System.out.println("\n");
    }

    /**
     * Prints all remaining Map Entries
     */
    @Test
    @Order(7)
    @DisplayName("Prints the final Test Map Values:")
    void testPrintAllMapAppointmentsLast() {
        assertDoesNotThrow(() -> {
            contactService.printAllContacts();
        });
    }
}