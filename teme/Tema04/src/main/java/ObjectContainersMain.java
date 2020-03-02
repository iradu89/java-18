

import util.*;

/**
 * Requirements:
 * <p>
 * 1. You need to store Persoane in a TreeSet. Define 2 Comparators (one for name - nume and one for age-varsta) that will be used when creating the TreeSet;
 * <p>
 * Add Persoane to the TreeSet; iterate throug the treeset and print the name and the age;
 * NOTE: if you are using a Comparator, the class Persoana should not implement Comparable anymore.
 * <p>
 * 2. You need to store some information about some persons: for one person, you need a list of his/her hobbies;
 * <p>
 * Define for Hobby a class that contains:
 * <p>
 * Name of hobby (String) – eq: cycling, swimming
 * Frequency (int) – how many times a week they practice it
 * List of Addresses where this hobby can be practiced (List<Adresa>)
 * You will use a HashMap
 * <p>
 * <Persoana, List<Hobby>>
 * <p>
 * NOTE: equals() from Persoana must be overriden
 * <p>
 * Add some information to this map; for a certain Persoana, print the names of the hobbies and the countries where it can be practiced
 * <p>
 * USE THE ALREADY DEFINED CLASSES: PERSOANA, SOMER, ANGAJAT, STUDENT; ADRESA; TARA;
 */

public class ObjectContainersMain {
    public static void main(String[] args) {

        /**
         * Part 1.
         */
        Persoana radu = new Student("Radu", "Bradu", "M", 20);
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana irina = new Angajat("Irina", "Ionescu", "F", 27, "SC Comex SRL",
                "Junior accounts payable", "contabilitate", 3000);
        Persoana radu2 = new Student("Radu", "Bradu", "M", 21);

        PersonContainer treeSet1 = new PersonContainer();
        treeSet1.add(radu);
        treeSet1.add(cata);
        treeSet1.add(irina);
        treeSet1.add(radu2);

        treeSet1.printTreeSetNameAndAge();

        System.out.println();

        /**
         * Part 2.
         */
        //Creating some countries
        Tara france = new Tara("France", "FR", "+33");
        Tara germany = new Tara("Germany", "DE", "+49");
        Tara switzerland = new Tara("Switzerland", "CH", "+41");
        Tara italy = new Tara("Italy", "IT", "+39");
        Tara romania = new Tara("Romania", "RO", "+40");

        //Creating hobbies and adding addresses
        Hobby skiing = new Hobby("Skiing", 5);
        skiing.addAddress(new Adresa(france, "Chamonix", "Place de l'Aiguille du Midi", 100));
        skiing.addAddress(new Adresa(switzerland, "Zermatt", "Winkelmattenweg", 65));

        Hobby racing = new Hobby("Racing", 3);
        racing.addAddress(new Adresa(germany, "Adenau", "Adenauer Frost", 11));
        racing.addAddress(new Adresa(italy, "Pavia", "Casteletto di Branduzzo", 215));
        racing.addAddress(new Adresa(switzerland, "Zwingen", "Chene-BourgStrasse", 21));

        Hobby fishing = new Hobby("Fishing", 2);
        fishing.addAddress(new Adresa(romania, "Caianu", "Lac Barai", 1));

        Hobby martialArts = new Hobby("Martial Arts", 5);
        martialArts.addAddress(new Adresa(romania, "Cluj-Napoca", "Aleea Stadionului", 1));
        martialArts.addAddress(new Adresa(italy, "Florence", "Sesto Fioretano", 16));

        Hobby swimming = new Hobby("Swimming", 6);
        swimming.addAddress(new Adresa(romania, "Cluj-Napoca", "Alexandru Vaida Voevod", 53));
        swimming.addAddress(new Adresa(france, "Paris", "Le street", 66));
        swimming.addAddress(new Adresa(italy, "Roma", "Borcuzzi street", 21));
        swimming.addAddress(new Adresa(germany, "Munchen", "Zweilinger Strasse", 90));

        Hobby fitness = new Hobby("Fitness", 4);
        fitness.addAddress(new Adresa(romania, "Cluj-Napoca", "Alexandru Vaida Voevod", 53));
        fitness.addAddress(new Adresa(switzerland, "Basel", "Allmendstrasse", 5));

        //HashMap of Persoana & List<Hobby>
        PersonContainerMap hashMap = new PersonContainerMap();
        hashMap.add(radu, swimming);
        hashMap.add(radu, fishing);
        hashMap.add(irina, racing);
        hashMap.add(irina, martialArts);
        hashMap.add(irina, fitness);
        hashMap.add(cata, skiing);
        hashMap.add(radu2, swimming);
        hashMap.add(radu2, skiing);

        hashMap.printHashMap();
    }
}
