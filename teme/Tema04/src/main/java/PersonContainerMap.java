import util.Adresa;
import util.Hobby;
import util.Persoana;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PersonContainerMap {
    private HashMap<Persoana, List<Hobby>> hashMap = new HashMap<Persoana, List<Hobby>>();

    public void add(Persoana persoana, Hobby hobby) {
        List<Hobby> hobbies;
        if (hashMap.containsKey(persoana)) {
            hobbies = hashMap.get(persoana);
        } else {
            hobbies = new LinkedList<Hobby>();
        }

        //making sure there are no duplicate hobbies
        if (!hobbies.contains(hobby)) {
            hobbies.add(hobby);
        }
        hashMap.put(persoana, hobbies);
    }

    //for testing purposes
    public List<Hobby> get(Persoana persoana) {
        return hashMap.get(persoana);
    }

    //for testing purposes
    public boolean contains(Persoana persoana) {
        return hashMap.containsKey(persoana);
    }

    public void printHashMap() {
        //printing the hobbies of each person
        for (Persoana key : hashMap.keySet()) {
            List<Hobby> hobbies = hashMap.get(key);
            System.out.println(key + " " + printHobbyAndCountry(hobbies) + "\n");
        }
    }

    private String printHobbyAndCountry(List<Hobby> hobbies) {
        String result = "";
        if (hobbies != null) {
            int i = 0;
            for (Hobby hobby : hobbies) {
                if (i >= 0) {
                    //formatting to separate Hobbies
                    result += "\n";
                }
                result = result + "Hobby = " + hobby.getName();
                result += " in the following countries: ";
                int j = 0;
                for (Adresa adresa : hobby.getAdrese()) {
                    if (j > 0) {
                        //formatting to separate Addresses
                        result += ", ";
                    }
                    result += adresa.getTara().getName();
                    j++;
                }
                i++;
            }
        }
        return result;
    }
}
