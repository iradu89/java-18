package phones.behaviour;

public interface Phone {
    // creates/adds new contact
    void addContact(String index, String phoneNum, String firstName, String lastName);

    // lists all contacts
    void listContacts();

    // sends a message to the specified phoneNumber
    void sendMessage(String phoneNum, String message);

    // lists all messages from that contact
    void listMessages(String phoneNum);

    // make a call to the phone number
    void call(String phoneNum);

    // lists all the calls made from that phone
    void viewHistory();
}
