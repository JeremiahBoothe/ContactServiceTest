package org.jeremiahboothe;

public class Contact {
    private final String contactID;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String address;

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
        nullCheck(firstName, "First Name");
        nullCheck(lastName, "Last Name");
        nullCheck(phoneNumber, "Phone Number");
        nullCheck(address, "Address");
        lengthCheckTen(contactID, "Contact ID");
        lengthCheckTen(firstName, "First Name");
        lengthCheckTen(lastName, "Last Name");
        lengthCheckTen(phoneNumber, "Phone Number");
        lengthCheckThirty(address, "Address");
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Updates existingContact retaining ID value and any other values that were null at update. You can update 1, 2, 3, or 4 values.
     * @param existingContact contact passed in for reconstruction.
     * @param firstName Contact First Name
     * @param lastName Contact Last Name
     * @param phoneNumber Contact Phone Number
     * @param address Contact Address
     * @throws NullPointerException throws NullPointerExceptions up the chain to be captured at test level
     * @throws IllegalArgumentException Passes IllegalArgumentException up the chain to be captured at test level
     */
    Contact(Contact existingContact,
            String firstName,
            String lastName,
            String phoneNumber,
            String address)
            throws NullPointerException,
            IllegalArgumentException {
            // Use the existing contact's ID
            contactID = existingContact.getContactID();
         // Update non-null values
        this.firstName = (firstName != null) ? firstName : existingContact.getFirstName();
        this.lastName = (lastName != null) ? lastName : existingContact.getLastName();
        this.phoneNumber = (phoneNumber != null) ? phoneNumber : existingContact.getPhoneNumber();
        this.address = (address != null) ? address : existingContact.getAddress();
        nullCheck(this.firstName, "First Name");
        nullCheck(this.lastName, "Last Name");
        nullCheck(this.phoneNumber, "Phone Number");
        nullCheck(this.address, "Address");
        lengthCheckTen(this.contactID, "Contact ID");
        lengthCheckTen(this.firstName, "First Name");
        lengthCheckTen(this.lastName, "Last Name");
        lengthCheckTen(this.phoneNumber, "Phone Number");
        lengthCheckThirty(this.address, "Address");
    }

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
     * Generic type length check to not exceed 10 and pass IllegalArgumentExceptions back up the chain if the value is greater than 10.
     * @param genericValue The generic typed value to check for length greater than 10.
     * @param errorMessage The error message for tracing back to the origin.
     * @param <T> Generic Type.
     */
    private <T> void lengthCheckTen(T genericValue, String errorMessage) {
        int length = String.valueOf(genericValue).length();
            if (length > 10) {
                throw new IllegalArgumentException(errorMessage + " cannot be longer than 10!");
            }
    }
     /**
      * Generic type length check to not exceed 30 and pass IllegalArgumentExceptions back up the chain if the value is greater than 30.
      * @param genericValue The generic typed value to check for length greater than 30.
      * @param errorMessage The error message for tracing back to the origin.
      * @param <T> Generic Type.
      */
    private <T> void lengthCheckThirty(T genericValue, String errorMessage) {
        int length = String.valueOf(genericValue).length();
        if (length > 30) {
            throw new IllegalArgumentException(errorMessage + " cannot be longer than 30!");
        }
    }

    /**
     * Retrieves the Contact ID
     * @return this.contactID
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