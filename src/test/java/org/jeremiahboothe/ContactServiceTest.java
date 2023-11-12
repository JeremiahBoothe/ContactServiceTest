package org.jeremiahboothe;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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
     *
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param address
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv")
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

            //contactService.displayValues();
            //Contact contact = contactService.getContactById(contactService.getCurrentContactID());
            assertNotNull(contactService.getContactById(contactService.getCurrentContactID()), "The added Contact Should not be Null");
            //assertEquals(contact, contactService.getContactById(contactService.getCurrentContactID()));
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        contactService.printAllContacts();
    }

    @Test
    void testCustomID() {
        assertDoesNotThrow(() -> {
            contactService.addContact("1000000000",
                    "Billy",
                    "Kurilko",
                    "4325556789",
                    "7523 Waterstreet");
            assertNotNull(contactService.getContactById(contactService.getCurrentContactID()), "The added Contact Should not be Null");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("21474836471", "Donald", "Hartley", "4325559894", "234 West Street");
            System.out.println("21464836471 is 11 digits and too long");
        });

    }
    @Test
    void testUpdateContact() {
        assertDoesNotThrow(() -> {

            contactService.addContact("1000", "But", "why", "too", "high a number");
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
        assertDoesNotThrow( () -> {
            //Todo: Assert it did update
            contactService.updateContact("1000",
                    "murp",
                    "Durp",
                    "butter",
                    "cookies");
        });
    }

    @Test
    void testPrintAllContacts() {
        contactService.printAllContacts();

    }
    @Disabled
    @Test
    void someTest() {

        System.out.println("\n");

        contactService.displayValues();

        System.out.println("\n");

        contactService.deleteContact("1");

        contactService.addContact("Lennry",
                "Balthazor",
                "4325559275",
                "333 Happy Place");

        contactService.deleteContact("3");

        try {
            contactService.deleteContact("99");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }


        contactService.addContact("Bob",
                "No One",
                "Nobody",
                "Happy");

        //Todo: Assert it did not overwrite



    try{
        contactService.addContact("Bob",
                "Buttersworth",
                "43259384",
                "Happy");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof NullPointerException,
                    "Unexpected exception type: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        contactService.printAllContacts();
    }

    @Test
    void generateUniqueID() {
    }

    @Test
    void nullCheck() {
    }

    @Test
    void getContactID() {
    }

    @Test
    void getLastName() {
    }

    @Test
    @Disabled
    void getFirstName() {
        contactService.getFirstName();

    }

    @Test
    @Disabled
    void testPhoneNumber() {
        contactService.getPhoneNumber();
    }

    @Test
    void address() {
    }


    @Test

    void displayValues() {
    }
}