package util;

import java.util.LinkedList;
import java.util.List;

public class Student extends Persoana {
    private List<Integer> grades = new LinkedList<Integer>();

    public Student(String name, String surname, String gender, int age) {
        super(name, surname, gender, age);
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getAge();
    }
}
