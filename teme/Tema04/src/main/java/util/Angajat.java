package util;

public class Angajat extends Persoana {
    private String companyName;
    private String position; //job position
    private String jobDescription;
    private int salary; //in RON

    public Angajat(String name, String surname, String gender, int age, String companyName, String position, String jobDescription, int salary) {
        super(name, surname, gender, age);
        this.companyName = companyName;
        this.position = position;
        this.jobDescription = jobDescription;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getAge();
    }
}
