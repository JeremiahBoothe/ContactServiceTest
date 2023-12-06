package org.jeremiahboothe;

public class Contact {
    private final String contactID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private final int lengthCheckShort = 10;
    private final int lengthCheckLong = 30;

    /**
     * Generic type nullCheck to universally check string values and pass NullPointerExceptions back up the chain if one is thrown.
     * @param genericValue The generic typed value to check for null.
     * @param errorMessage The error message for tracing back to the origin.
     * @param <T> Generic Type.
     */
    private <T> void nullCheck(T genericValue, String errorMessage) {
        if (genericValue == null) {
            throw new NullPointerException(errorMessage + " cannot be null!");
        }
    }

    /**
     * Generic type length check, made it more universal, now it receives an integer value of allowedLength, to be more flexible and redefined in the final lengths at the top or by each setter.
     * @param genericValue The generic typed value to check for length greater than 10 or 30.
     * @param errorMessage The error message for tracing back to the origin.
     * @param allowedLength The allowed maximum not to be exceeded by each setter.
     * @param <T> Generic Type.
     */
    private <T> void lengthCheck(T genericValue, String errorMessage, int allowedLength) {
        int length = String.valueOf(genericValue).length();
        if (length > allowedLength) {
            throw new IllegalArgumentException(errorMessage + " cannot be longer than " + allowedLength + "!");
        }
    }

    /**
     * Constructor for new Contact Creation. Constructor is designed to prevent object instantiation upon failed null check or failed length check. Passes errors up the chain to be captured at test level.
     * @param contactID Generated or UserInputID
     * @param firstName Contact First Name
     * @param lastName Contact Last Name
     * @param phoneNumber Contact Phone Number
     * @param address Contact Address
     * @throws NullPointerException throws NullPointerExceptions up the chain to be captured at test level
     * @throws IllegalArgumentException Passes IllegalArgumentException up the chain to be captured at test level
     */
    Contact(String contactID,
            String firstName,
            String lastName,
            String phoneNumber,
            String address)
            throws NullPointerException,
            IllegalArgumentException {
        nullCheck(contactID, "Contact ID");
        lengthCheck(contactID, "Contact ID", lengthCheckShort);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        this.contactID = contactID;
    }

    /**
     * Sets the First Name for the contact.
     * @param firstName First Name to apply to contact.
     */
    void setFirstName(String firstName) {
        String errorIdentifier = "First Name";

        nullCheck(firstName, errorIdentifier);
        lengthCheck(firstName, errorIdentifier, lengthCheckShort);

        this.firstName = firstName;
    }

    /**
     * Sets the Last Name for the contact.
     * @param lastName Last Name to apply to the contact.
     */
    void setLastName(String lastName) {
        String errorIdentifier = "Last Name";

        nullCheck(lastName, errorIdentifier);
        lengthCheck(lastName, errorIdentifier, lengthCheckShort);

        this.lastName = lastName;
    }

    /**
     * Sets the Phone Number for the contact.
     * @param phoneNumber Phone Number to apply to the contact.
     */
    void setPhoneNumber(String phoneNumber) {
        String errorIdentifier = "Phone Number";

        nullCheck(phoneNumber, errorIdentifier);
        lengthCheck(phoneNumber, errorIdentifier, lengthCheckShort);

        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the Address for the contact.
     * @param address Address to apply to the contact.
     */
    void setAddress(String address) {
        String errorIdentifier = "Address";

        nullCheck(address, errorIdentifier);
        lengthCheck(address, errorIdentifier, lengthCheckLong);

        this.address = address;
    }

    /**
     * Retrieves the Contact ID
     * @return contactID
     */
    String getContactID(){
        return this.contactID;
    }

    /**
     * Retrieves the Contact Last name
     * @return lastName
     */
    String getLastName(){
        return this.lastName;
    }

    /**
     * Retrieves the Contact First Name
     * @return firstName
     */
    String getFirstName(){
        return firstName;
    }

    /**
     * Retrieves the Contact Phone Number
     * @return phoneNumber
     */
    String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Retrieves the Contact Address
     * @return address
     */
    String getAddress() {
        return address;
    }

    /**
     * Prints Contact Values
     */
    void displayValues() {
        System.out.println("ID: " + getContactID());
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Address: " + getAddress());
    }
}