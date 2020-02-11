package phones.huawei;

import phones.behaviour.Contact;
import phones.behaviour.Message;
import phones.behaviour.Phone;

import java.util.ArrayList;
import java.util.List;

public abstract class Huawei implements Phone {
    private int deviceBatteryConsumption = 0;

    private String color;
    private String material;
    //final BATTERY_LIFE as per specifications
    private final int BATTERY_LIFE;
    //final IMEI because IMEI doesn't change
    private final String IMEI;

    private List<Message> messages = new ArrayList<Message>();
    private List<Contact> listContacts = new ArrayList<Contact>();
    private List<String> calls = new ArrayList<String>();

    public Huawei(String color, String material, String imei, int batteryLife) {
        this.color = color;
        this.material = material;
        this.IMEI = imei;
        this.BATTERY_LIFE = batteryLife;
    }

    public void addContact(String index, String phoneNum, String firstName, String lastName) {
        listContacts.add(new Contact(index, phoneNum, firstName, lastName));
    }

    public void listContacts() {
        for (Contact contact : listContacts) {
            System.out.println(contact);
        }
    }

    public void listMessages(String phoneNum) {
        for (Message message : messages) {
            if (message.getPhoneNum().equals(phoneNum)) {
                System.out.println(message);
            }
        }
    }

    public void viewHistory() {
        for (String call : calls) {
            System.out.println(call);
        }
    }

    public void sendMessage(String phoneNum, String message) {
        if (deviceBatteryConsumption >= BATTERY_LIFE) {
            System.out.println("Sorry, no more battery left for messages");
            return;
        }
        Message resultMessage = new Message(phoneNum, message);
        if (resultMessage.getPhoneNum() != null && resultMessage.getMessage() != null) {
            messages.add(resultMessage);
            deviceBatteryConsumption += 1;
        }
    }

    public void call(String phoneNum) {
        // cannot make a call if there are less than 2 hours available
        if (deviceBatteryConsumption + 1 >= BATTERY_LIFE) { //+ 1 to account for odd numbers in case the user also sent messages.
            System.out.println("Sorry, no more battery left for calls");
            return;
        }
        calls.add(phoneNum);
        deviceBatteryConsumption += 2;
    }

}
