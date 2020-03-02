import util.Persoana;

import java.util.Comparator;
import java.util.TreeSet;

public class PersonContainer {
    //using a comparator by name and age
    private TreeSet<Persoana> treeSet = new TreeSet<Persoana>(new Comparator<Persoana>() {
        public int compare(Persoana person1, Persoana person2) {
            String thisName = person1.getName();
            String comparedName = person2.getName();
            if (!thisName.equals(comparedName)) { //if names are not identical then compare only by name
                return thisName.compareTo(comparedName);
            } else { //if names are identical then compare them according to the age by ascending order;
                return person1.getAge() - person2.getAge();
            }
        }
    });

    //for testing purposes
    public Persoana getFirstElement() {
        return treeSet.first();
    }

    public boolean add(Persoana persoana) {
        return treeSet.add(persoana);
    }

    public void printTreeSetNameAndAge() {
        for (Persoana persoana : treeSet) {
            System.out.println(persoana);
        }
    }
}
