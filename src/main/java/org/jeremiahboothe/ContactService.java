package org.jeremiahboothe;

import java.util.HashMap;

/**
 * Created as a Singleton, ContactService, handles the operations and creation of the map and hashmap. Upon initialization the constructor instantiates the Hashmap.
 */
public class ContactService {
    // Other fields and methods remain unchanged
    private final HashMap<String, Contact> contactMap;
    private static final ContactService CONTACT_SERVICE = new ContactService();

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
        return CONTACT_SERVICE;
    }

    /**
     * Adds Contact to contact map.
     * @param contact the contact to add to the map
     */
    void addContact(Contact contact) {
        if (contactMap.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID: " + contact.getContactID() + " already exists!");
        }
        contactMap.put(contact.getContactID(), contact);
    }

    /**
     * Creates the Contact from the information provided.
     * @param contactId User Input ID of Contact
     * @param firstName User Input First Name of Contact
     * @param lastName User Input Last Name of Contact
     * @param phoneNumber User Input Phone Number of Contact
     * @param address User Input Address of Contact
     * @return Contact Created contact
     */
    Contact createNewContactToAddToMap(String contactId, String firstName, String lastName, String phoneNumber, String address) {
        return new Contact(contactId, firstName, lastName, phoneNumber, address);
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

    /**
     * Retrieves Contact by ID
     * @param contactID ID of Contact
     * @return Contact
     */
    Contact getContactById(String contactID) {
        return contactMap.get(contactID);
    }

    /**
     * Retrieves Address of current Contact
     * @return String
     */
    String getAddress(String contactId) {
        return getContactById(contactId).getAddress();
    }

    /**
     * Retrieves Phone Number of current Contact
     * @return String
     */
    String getPhoneNumber(String contactId) {
        return getContactById(contactId).getPhoneNumber();
    }

    /**
     * Retrieves First Name of current Contact
     * @return String
     */
    String getFirstName(String contactId) {
        return getContactById(contactId).getFirstName();
    }

    /**
     * Retrieves Last Name of current Contact
     * @return String
     */
    String getLastName(String contactId) {
        return getContactById(contactId).getLastName();
    }

    /**
     * Updates Contact First Name in the map, by Id.
     * @param contactId Contact Id to update
     * @param firstName Contact Date to update
     */
    void updateFirstName(String contactId, String firstName){
        Contact contact = contactMap.get(contactId);
        contact.setFirstName(firstName);
    }

    /**
     * Updates Contact Last Name in the map, by Id.
     * @param contactId Contact Id to update
     * @param lastName Contact Date to update
     */
    void updateLastName(String contactId, String lastName){
        Contact contact = contactMap.get(contactId);
        contact.setLastName(lastName);
    }

    /**
     * Updates Contact Phone Number in the map, by Id.
     * @param contactId Contact Id to update
     * @param phoneNumber Contact Date to update
     */
    void updatePhoneNumber(String contactId, String phoneNumber){
        Contact contact = contactMap.get(contactId);
        contact.setPhoneNumber(phoneNumber);
    }

    /**
     * Updates Contact Address in the map, by Id.
     * @param contactId Contact Id to update
     * @param address Contact Date to update
     */
    void updateAddress(String contactId, String address){
        Contact contact = contactMap.get(contactId);
        contact.setAddress(address);
    }

    /**
     * Displays values of contact.
     * @param contactId Id of contact to display values of.
     */
    void displayValues(String contactId) {
        getContactById(contactId).displayValues();
    }

    /**
     * Iterates through the hashmap and prints all contacts.
     */
    void printAllContacts() {
        for (HashMap.Entry<String, Contact> entry : CONTACT_SERVICE.getAllContacts().entrySet()) {
            String contactId = entry.getKey();
            Contact retrievedContact = entry.getValue();
            System.out.println("\nRetrieved Contact from Index " + contactId + ":");
            retrievedContact.displayValues();
            System.out.println("\n");
        }
    }

    /**
     * Function to retrieve the contact map for printing all contacts. Made Private since nothing external accesses it.
     * @return HashMap Contains all Contact entries
     */
    private HashMap<String, Contact> getAllContacts() {
        return contactMap;
    }
}