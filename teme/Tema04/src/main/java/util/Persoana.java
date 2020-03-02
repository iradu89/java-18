package util;

public class Persoana {
    private int age;
    private String name;
    private String surname;
    private String gender;

    public Persoana(String name, String surname, String gender, int age) {
        this.gender = gender;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() * this.getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return age == persoana.age &&
                name.equals(persoana.name);
    }
}