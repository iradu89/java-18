package phones;

import phones.apple.IphoneX;
import phones.huawei.P30Pro;
import phones.behaviour.Phone;
import phones.samsung.SamsungGalaxyS6;

/**
 * TODO
 * Java OOP
 * Requirements
 * <p>
 * All phones offer the following behavior:
 * - you can create new contacts
 * - you can see existing contacts
 * - you can send a text message
 * - you can see all messages for a specific contact
 * - you can make a call
 * - you can see all calls history
 * <p>
 * Implement a phone hierarchy where:
 * - A phone can be of certain manufacturers and it can have a specific model (Phone - Samsung - SamsungGalaxyS6)
 * - properties which cannot change: battery life (in number of hours)
 * - properties that are configurable: color, material
 * - properties that are instance specific: imei
 * <p>
 * Behavior:
 * - a message can have maximum 500 chars
 * - every time a message is send an hour is taken from the battery life
 * - every time a call is made two hours are taken from the battery life
 * <p>
 * API:
 * Phone phone= new Samsung(); // shouldn't compile
 * Phone phone = new SamsungGalaxy6();
 * <p>
 * phone.addContact("1", "phone number", "fist name", "last name");
 * phone.addContact("2", "phone number", "fist name", "last name");
 * phone.listContacts();
 * <p>
 * // send a message to the first contact from the previous listed
 * // max number of characters - 100
 * phone.sendMessage("phone number", "message content");
 * phone.listMessages("phone number");
 * <p>
 * // send a message to the second contact from the previous listed
 * phone.call("phone number");
 * phone.viewHistory();
 * <p>
 * Tasks:
 * - implement the classes, abstract classes and interfaces according to the given information
 * - create at least 2 phone brands with 2 models for each
 * - create 2 new contacts
 * - send a message to the first contact
 * - make a call to the second contact
 */

public class PhoneMain {
    public static void main(String[] args) {

        //Mandatory TASKS:
        Phone phone = new SamsungGalaxyS6();
        phone.addContact("1", "0758995778", "Radu", "Bradu");
        phone.addContact("2", "0788996551", "Gabriel", "Lulciuc");

        phone.sendMessage("0758995778", "Acesta e un mesaj Acesta e un mesaj Acesta e un mesaj");

        phone.call("0788996551");

        //Extra TASKS:
        System.out.println("Messages: ");
        phone.listMessages("0758995778");
        System.out.println("\nContacts: ");
        phone.listContacts();
        System.out.println("\nView History: ");
        phone.viewHistory();

        //color & material configurable
        Phone anotherPhone = new SamsungGalaxyS6("violet", "molded plastic");
    }
}
