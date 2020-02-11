package phones.behaviour;


/**
 * The specifications called for ALL messages to be of 500 char max, regardless of the type of phone.
 */
public class Message {
    private static final int MSG_MAX_LENGTH = 500;
    private String phoneNum;
    private String message;

    public Message(String phoneNum, String message) {
        if (message.length() > MSG_MAX_LENGTH) {
            System.out.println("Message max length is 500");
            this.phoneNum = null;
            return;
        }
        this.phoneNum = phoneNum;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public String toString() {
        return this.phoneNum + " " + this.message;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
