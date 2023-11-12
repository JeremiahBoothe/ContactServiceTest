package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: test to assure MapKey matches object id always?
 */
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
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param address
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
     * todo
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

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contactService
                    .addContact("1000000000",
                            "Billy",
                            "Kurilko",
                            "4325556789",
                            "7523 Waterstreet");

        });
        assertEquals("Contact ID: 1000000000 already exists!", exception.getMessage());
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
     * todo
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
     * todo
     */
    @Test
    void testDeleteContact() {
        assertDoesNotThrow(() -> {
            contactService.addContact("1",
                    "asdf",
                    "asdfgh",
                    "340531",
                    "234345");
            contactService.deleteContact("1"); // Assuming "2" does not exist
            assertNull(contactService.getContactById("1"));
        });

        try {
            contactService.deleteContact("1"); // Assuming "2" does not exist

        } catch (NullPointerException e) {
            assertTrue(true,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    /**
     * todo
     */
    @Test
    void testPrintAllContacts() {
        contactService.printAllContacts();
    }


    /**
     * todo:
     */
    @Test
    void getContactID() {
        contactService.addContact("599","Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");
        System.out.println(contactService.getContactID());
    }

    /**
     * todo:
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
     * Todo:
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
     * done
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
     * done
     */
    @Test
    void testGetAddress() {
        assertDoesNotThrow(() -> {
            contactService.addContact("99", "Lennry",
                    "Balthazor",
                    "4325559275",
                    "333 Happy Place");
            assertEquals(contactService.getAddress(), "333 Happy Place");
        });
        assertThrows(NullPointerException.class, () -> {
            assertNull(contactService.getContactById("777").getAddress());
        });
    }
}