package org.jeremiahboothe;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created as a Singleton, ContactService, handles the operations and creation of the map and hashmap. Upon initialization the constructor instantiates the Hashmap.
 */
public class ContactService {
    // Other fields and methods remain unchanged
    private final HashMap<String, Contact> contactMap;
    private String currentContactID;
    private static final ContactService contactService = new ContactService();

    /**
     * Constructor for ContactService instantiates contactMap when constructed.
     */
    private ContactService() {
        this.contactMap = new HashMap<>();
    }

    /**
     * Retrieves contactService to operate as a singleton.
     * @return ContactService contactService
     */
    static ContactService getInstance() {
        return contactService;
    }
    /**
     * Checks map for equivalent ID uses Long to generate a unique ID and compares the value of the String to the keys of the HashMap to determine if that key already exists, if not that ID is used, if it does exist, a new ID is created until one is found that does not exist.
     * @return newID When id not found.
     */
    static String generateUniqueID() {
        AtomicLong contactIDGenerator = new AtomicLong();
        String newId;
        do {
            // Generate a new unique ID passing over any that already exist in the contact map
            newId = String.valueOf(contactIDGenerator.incrementAndGet());
        } while (contactService.contactMap.containsKey(newId)); // Check if the ID already exists in the map
        return newId;
    }

    /**
     * Stores active contactID for reference to add & update Contacts
     * @param newID new ContactID, whether user input or generated
     */
    void setCurrentContactID(String newID) {
        this.currentContactID = newID;
    }

    /**
     * Retrieves the ID of the currently active contact.
     * @return currentContactID
     */
    String getCurrentContactID() {
        return currentContactID;
    }
    /**
     * Generates a uniqueID if user did not input an ID, creates new Contact with ID, adds the Contact to the Map, and sets currentContactID to newID as reference.
     *
     * @param firstName   User input First Name of Contact
     * @param lastName    User input Last Name of Contact
     * @param phoneNumber User input Phone Number of Contact
     * @param address     User input Address of Contact
     */
     void addContact(String firstName,
                     String lastName,
                     String phoneNumber,
                     String address) {
        String newID = generateUniqueID();
        Contact contact = new Contact(newID,
                firstName,
                lastName,
                phoneNumber,
                address);
        contactMap.put(newID, contact);
        setCurrentContactID(newID);
     }

    /**
     * Adds Contact when user inputs preferred ID
     *
     * @param userID      User Input ID of Contact
     * @param firstName   User Input First Name of Contact
     * @param lastName    User Input Last Name of Contact
     * @param phoneNumber User Input Phone Number of Contact
     * @param address     User Input Address of Contact
     */
    void addContact(String userID,
                    String firstName,
                    String lastName,
                    String phoneNumber,
                    String address) {
        if (contactMap.containsKey(userID)) {
            throw new IllegalArgumentException("Contact ID: " + userID + " already exists!");
        }
        Contact contact = new Contact(userID,
                firstName,
                lastName,
                phoneNumber,
                address);
        contactMap.put(userID, contact);
        setCurrentContactID(userID);
    }

    /**
     * Retrieves Contact by ID
     * @param contactID ID of Contact
     * @return Contact
     */
    Contact getContactById(String contactID) {
        return contactMap.get(contactID);
    }

    /**
     * Displays values of current Contact
     */
    void displayValues() {
        contactService.getContactById(contactService.getCurrentContactID()).displayValues();
    }

    /**
     * Retrieves Address of current Contact
     * @return String
     */
    String getAddress() {
            return contactService.getContactById(contactService.getCurrentContactID()).getAddress();
    }

    /**
     * Retrieves Phone Number of current Contact
     * @return String
     */
    String getPhoneNumber() {
        return contactService.getContactById(contactService.getCurrentContactID()).getPhoneNumber();

    }

    /**
     * Retrieves First Name of current Contact
     * @return String
     */
    String getFirstName() {
        return contactService.getContactById(contactService.getCurrentContactID()).getFirstName();
    }

    /**
     * Retrieves Last Name of current Contact
     * @return String
     */
    String getLastName() {
        return contactService.getContactById(contactService.getCurrentContactID()).getLastName();
    }

    /**
     * Retrieves ID of current Contact
     * @return contactID of current Contact
     */
    String getContactID() {
        return contactService.getContactById(contactService.getCurrentContactID()).getContactID();
    }
    /**
     * Iterates through the hashmap and prints all contacts. Could replace contactService.getAllContacts().entrySet() with contactMap.entrySet() but not doing so for now in case there is further use for getAllContacts, later on.
     */
    void printAllContacts() {
        for (HashMap.Entry<String, Contact> entry : contactService.getAllContacts().entrySet()) {
            String contactId = entry.getKey();
            Contact retrievedContact = entry.getValue();
            System.out.println("\nRetrieved Contact from Index " + contactId + ":");
            retrievedContact.displayValues();
        }
    }

    /**
     * Function to retrieve the contact map for printing all contacts. Made Private since nothing external accesses it.
     * @return HashMap
     */
    private HashMap<String, Contact> getAllContacts() {
        return contactMap;
    }

    /**
     * Updates Contact or throws error, values passed as null will be ignored in the constructor only non-null values will be updated.
     * @param contactID ID of contact to update
     * @param newFirstName New contact First Name
     * @param newLastName New contact Last Name
     * @param newPhoneNumber New contact Phone Number
     * @param newAddress New contact Address
     */
    void updateContact(String contactID,
                              String newFirstName,
                              String newLastName,
                              String newPhoneNumber,
                              String newAddress) throws NullPointerException {
        Contact existingContact = contactMap.get(contactID);
        if (existingContact != null) {
            // Create a new contact with updated values
            Contact updatedContact = new Contact(existingContact,
                    newFirstName,
                    newLastName,
                    newPhoneNumber,
                    newAddress);
            // Update the contact in the map
            contactMap.put(contactID, updatedContact);
            System.out.println("Contact with ID "
                    + contactID
                    + " updated successfully!");
        } else {
            throw new NullPointerException("Contact with ID: " + contactID + " not found.");
        }
    }

    /**
     * Deletes a Contact by ID or throws an exception if the ID does not exist in the HashMap
     * @param contactID Contact ID to delete
     * @throws NullPointerException When ID is not in the map.
     */
    void deleteContact(String contactID) throws NullPointerException {
        if (contactMap.containsKey(contactID)) {
            contactMap.remove(contactID);
            System.out.println("Contact with ID: "
                    + contactID
                    + " deleted successfully!");
        } else {
            throw new NullPointerException("Contact ID: "
                    + contactID
                    + " does not exist");
        }
    }
}