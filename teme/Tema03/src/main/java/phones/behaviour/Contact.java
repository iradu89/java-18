package phones.behaviour;

public class Contact {
    private String index;
    private String firstName;
    private String lastName;
    private String phoneNum;

    //contact as per specifications
    public Contact(String index, String phoneNum, String firstName, String lastName) {
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return this.index + " " + this.firstName + " " + this.lastName + " " + this.phoneNum;
    }
}
