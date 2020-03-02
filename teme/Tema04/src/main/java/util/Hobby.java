package util;

import java.util.LinkedList;
import java.util.List;

public class Hobby {
    private String name;
    private int frequency; //how many times per week it can be practiced
    private List<Adresa> adrese = new LinkedList<Adresa>();

    public Hobby(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public void addAddress(Adresa adresa) {
        adrese.add(adresa);
    }

    public List<Adresa> getAdrese() {
        return adrese;
    }

    public String getName() {
        return name;
    }
}
