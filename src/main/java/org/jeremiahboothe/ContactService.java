package org.jeremiahboothe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created as a Singleton, ContactService, handles the operations and creation of the map and hashmap. Upon instantiation the constructor creates the Hashmap.
 */
public class ContactService {
    private int currentContactID;
    private static final HashMap<Integer, Contact> contactMap = new HashMap<>();
    private static final ContactService contactService = new ContactService();

    /**
     * Constructs ContactService with HashMap to store the UniqueID's as the key and Contact objects as values.
     */
    private ContactService() {
    }

    /**
     * @return newID
     */
    public static int generateUniqueID() {
        AtomicInteger contactIDGenerator = new AtomicInteger();
        int newId;
        do {
            // Generate a new unique ID passing over any that already exist in the contact map
            newId = contactIDGenerator.incrementAndGet();
        } while (contactMap.containsKey(newId)); // Check if the ID already exists in the map
        return newId;
    }

    /**
     *
     * @param newID
     */
    private void setCurrentContactID(int newID) {
        this.currentContactID = newID;
    }

    /**
     *
     * @return currentContactID
     */
    public int getCurrentContactID() {
        return currentContactID;
    }

    /**
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param address
     * @return new Contact
     */
    Contact addContact(String firstName,
                       String lastName,
                       String phoneNumber,
                       String address) {
        int newID = generateUniqueID();
        Contact contact = new Contact(newID,
                firstName,
                lastName,
                phoneNumber,
                address);
        contactMap.put(newID, contact);
        setCurrentContactID(newID);
        return contact;
    }

    /**
     * @param userID
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param address
     * @return new Contact
     */
    Contact addContact(int userID,
                       String firstName,
                       String lastName,
                       String phoneNumber,
                       String address) throws IllegalArgumentException {
        if (contactMap.containsKey(userID)) {
            System.out.println("There's already a value there!");
            throw new IllegalArgumentException("ILLEGAL!");
        }

        Contact contact = new Contact(userID,
                firstName,
                lastName,
                phoneNumber,
                address);
        contactMap.put(userID, contact);
        setCurrentContactID(userID);
        return contact;
    }

    /**
     * @return contactMap
     */
    public Map<Integer, Contact> getAllContacts() {
        return contactMap;
    }

    /**
     * @return contactService
     */
    public static ContactService getInstance() {
        return contactService;
    }

    /**
     * @param contactID
     * @return contactMap
     */
    public Contact getContactById(int contactID) {
        return contactMap.get(contactID);
    }

    /**
     *
     *
     */
    public void displayValues() {
        contactService.getContactById(contactService.getCurrentContactID()).displayValues();
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return contactService.getContactById(contactService.getCurrentContactID()).getAddress();
    }

    /**
     *
     */
    public void getPhoneNumber() {
        contactService.getContactById(contactService.getCurrentContactID());
    }

    /**
     *
     */
    public void getFirstName() {
        contactService.getContactById(contactService.getCurrentContactID());
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return contactService.getContactById(contactService.getCurrentContactID()).getLastName();
    }

    /**
     *
     * @return
     */
    public int getContactID() {
        return contactService.getContactById(contactService.getCurrentContactID()).getContactID();
    }

    /**
     *
     */
    public void printAllContacts() {
        for (HashMap.Entry<Integer, Contact> entry : contactService.getAllContacts().entrySet()) {
            int contactId = entry.getKey();
            Contact retrievedContact = entry.getValue();
            System.out.println("\nRetrieved Contact from Index " + contactId + ":");
            retrievedContact.displayValues();
        }
    }

    /**
     * @param contactID
     * @param newFirstName
     * @param newLastName
     * @param newPhoneNumber
     * @param newAddress
     */
    public void updateContact(int contactID,
                              String newFirstName,
                              String newLastName,
                              String newPhoneNumber,
                              String newAddress) {
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
                    + " updated successfully.");
        } else {
            System.out.println("Error: Contact with ID "
                    + contactID
                    + " not found.");
        }
    }

    /**
     * @param contactID
     */
    public void deleteContact(int contactID) throws NullPointerException {
        Contact removedContact = contactMap.remove(contactID);
        if (removedContact != null) {
                System.out.println("Contact with ID "
                        + contactID
                        + " deleted successfulzly.");
        } else {
            throw new NullPointerException("deleteContact: ID does not exist");

        }
    }
}

