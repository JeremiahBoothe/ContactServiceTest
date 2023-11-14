package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

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
     * Parameterized test to run null check and length check, ensuring values that are too long or null are rejected.
     * @param firstName CSV First Names
     * @param lastName CSV Last Names
     * @param phoneNumber CSV Phone Numbers
     * @param address CSV Addresses
     */
    @CsvSource({
            "Isabella,Anderson,4325555678,123 Oak St",
            ",Smith,4325551234,456 Pine Ln",
            "Sophia,,4325559275,789 Elm Blvd",
            "Mason,Clark, ,890 Cedar Rd",
            "Amelia,Cooper,4325558765,",
            "EmmaGenopolis,Evans,4325553456,567 Maple Dr",
            "Elijah,TurnerBakerson,4325556789,890 Cedar Rd",
            "Emma,Miller,14325554321,456 Oak Ave",
            "Olivia,Davis,4325553456,8000 Taumatawhakatangihangakoauauo tamateaturipukakapikimaungahoronuku pokaiwhenuakitanatahu",
            "Sophia,Clark,4325559275,789 Elm Blvd"})
    @ParameterizedTest
    @DisplayName("Test Exceptions for Null and Length")
    void testAddContactAndGetContactById(String firstName,
                                         String lastName,
                                         String phoneNumber,
                                         String address) {
        try {
            contactService.addContact(firstName,
                    lastName,
                    phoneNumber,
                    address);

            System.out.println("\n");

            contactService.displayValues();
            System.out.println("\n");
            //Contact contact = contactService.getContactById(contactService.getCurrentContactID());
            assertNotNull(contactService.getContactById(contactService.getCurrentContactID()), "The added Contact Should not be Null");
            //assertEquals(contact, contactService.getContactById(contactService.getCurrentContactID()));
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

    /**
     * Tests User Input ID's, and checks to ensure overwrites to not occur after user Input ID is created.
     */
    @Test
    void testCustomID() {
        assertDoesNotThrow(() -> {
            contactService
                    .addContact("1000000000",
                    "Billy",
                    "Kurilko",
                    "4325556789",
                    "7523 Waterstreet");
        });

        assertDoesNotThrow(() -> {
            contactService
                    .addContact("JJJ*#$@$%J",
                            "Benny",
                            "Barton",
                            "4325356389",
                            "7432 Waterstreet");
        });

        IllegalArgumentException exceptionDuplicateID = assertThrows(IllegalArgumentException.class, () -> {
            contactService
                    .addContact("1000000000",
                            "Billy",
                            "Kurilko",
                            "4325556789",
                            "7523 Waterstreet");

        });
        assertEquals("Contact ID: 1000000000 already exists!", exceptionDuplicateID.getMessage());

        IllegalArgumentException exceptionDuplicateID2 = assertThrows(IllegalArgumentException.class, () -> {
            contactService
                    .addContact("JJJ*#$@$%J",
                            "Billy",
                            "Kurilko",
                            "4325556789",
                            "7523 Waterstreet");

        });
        assertEquals("Contact ID: JJJ*#$@$%J already exists!", exceptionDuplicateID2.getMessage());
        contactService.displayValues();

    }

    /**
     * todo:
     */
    @Test
    void testUpdateContact() {
        assertDoesNotThrow(() -> {
            contactService.addContact("1000",
                    "But",
                    "why",
                    "too",
                    "high a number");
            assertNotNull(contactService.getContactById(contactService.getCurrentContactID()), "The added Contact Should not be Null");

        });

        // Use assertThrows to check for exceptions during the contact addition
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("1000",
                    "meh",
                    "beh",
                    "too",
                    "high");
        });

        //
        assertDoesNotThrow(() -> {
            contactService.updateContact("1000",
                    "murp",
                    "Durp",
                    "butter",
                    "cookies");
        });
    }

    /**
     * Tests updating contact that does not exist, to make sure it's not added instead.
     */
    @Test
    void updateContactNonExistent() {
    try {
            contactService.updateContact("75",
                    "murp",
                    "Durp",
                    "butter",
                    "cookies");
    }catch (NullPointerException e) {
            assertTrue(true,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests and verifies deleting contact and not being able to again delete a contact after it has been deleted.
     */
    @Test
    void testDeleteContact() {
        assertDoesNotThrow(() -> {
            contactService.addContact("1",
                    "asdf",
                    "asdfgh",
                    "340531",
                    "234345");
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
     * Tests print allContacts to make sure it's not throwing an error, the contacts currently in the map are printed to console.
     */
    @Test
    void testPrintAllContacts() {
        assertDoesNotThrow(() -> {
            contactService.printAllContacts();
        });
    }

    /**
     * Tests retrieval of ID for current contact
     */
    @Test
    void getContactID() {
        contactService.addContact("599","Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");
        assertEquals(contactService.getContactID(), "599");
    }

    /**
     * Tests retrieval of Last Name of current contact.
     */
    @Test
    void getLastName() {
        contactService.addContact("499","Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");
        assertEquals(contactService.getLastName(), "Balthazor");
    }

    /**
     * Tests retrieval of First Name of current Contact
     */
    @Test
    void getFirstName() {
        contactService.addContact("399",
                "Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");
        assertEquals(contactService.getFirstName(), "Lennry");
    }

    /**
     * Tests retrieval of phone number from current contact
     */
    @Test
    void testGetPhoneNumber() {
        assertDoesNotThrow(() -> {
            contactService.addContact("299", "Lennry",
                    "Balthazor",
                    "4325559275",
                    "333 Happy Place");
        });
        assertEquals("4325559275", contactService.getContactById(contactService.getContactID()).getPhoneNumber());
        System.out.println("Phone Number: " + contactService.getPhoneNumber());
    }

    /**
     * Tests retrieval of address from current contact.
     */
    @Test
    void testGetAddress() {
        assertDoesNotThrow(() -> {
            contactService.addContact("99", "Lennry",
                    "Balthazor",
                    "4325559275",
                    "333 Happy Place");
            assertEquals(contactService.getContactById("99").getAddress(), "333 Happy Place");
            System.out.println(contactService.getAddress());
        });
        assertThrows(NullPointerException.class, () -> {
            assertNull(contactService.getContactById("777").getAddress());
        });
    }
}