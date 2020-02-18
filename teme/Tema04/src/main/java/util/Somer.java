package util;

public class Somer extends Persoana {
    private String reasonOfDismissal; //currently just string for simplicity

    public Somer(String name, String surname, String gender, int age, String reasonOfDismissal) {
        super(name, surname, gender, age);
        this.reasonOfDismissal = reasonOfDismissal;
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getAge();
    }
}
