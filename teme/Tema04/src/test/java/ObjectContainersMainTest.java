
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectContainersMainTest {
    //treeset
    PersonContainer treeSet;

    //hashmap
    PersonContainerMap hashMap;

    /**
     * Creating Hobbies with addresses and countries for the HashMap
     */

    //Creating some countries
    Tara france = new Tara("France", "FR", "+33");
    Tara germany = new Tara("Germany", "DE", "+49");
    Tara switzerland = new Tara("Switzerland", "CH", "+41");
    Tara italy = new Tara("Italy", "IT", "+39");

    //Creating hobbies
    Hobby skiing = new Hobby("Skiing", 5);
    Hobby racing = new Hobby("Racing", 3);

    @BeforeEach
    public void setup() {
        //treeset
        treeSet = new PersonContainer();

        //HashMap
        hashMap = new PersonContainerMap();

        //adding addresses to hobbies
        skiing.addAddress(new Adresa(france, "Chamonix", "Place de l'Aiguille du Midi", 100));
        skiing.addAddress(new Adresa(switzerland, "Zermatt", "Winkelmattenweg", 65));

        racing.addAddress(new Adresa(germany, "Adenau", "Adenauer Frost", 11));
        racing.addAddress(new Adresa(italy, "Pavia", "Casteletto di Branduzzo", 215));
        racing.addAddress(new Adresa(switzerland, "Zwingen", "Chene-BourgStrasse", 21));
    }

    /**
     * TreeSet Tests
     */
    @Test
    public void treeset_test_add_student() {
        Persoana radu = new Student("Radu", "Bradu", "M", 20);

        assertTrue(treeSet.add(radu));
    }

    @Test
    public void treeset_test_add_angajat() {
        Persoana irina = new Angajat("Irina", "Ionescu", "F", 27, "SC Comex SRL",
                "Junior accounts payable", "contabilitate", 3000);

        assertTrue(treeSet.add(irina));
    }

    @Test
    public void treeset_test_add_somer() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");

        assertTrue(treeSet.add(cata));
    }

    @Test
    public void treeset_test_add_duplicates() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana cata2 = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");

        assertTrue(treeSet.add(cata));
        assertFalse(treeSet.add(cata2));
    }

    @Test
    public void treeset_test_add_persons_with_same_name_different_age() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana cata2 = new Somer("Catalin", "Popescu", "M", 38, "Demisie proprie");

        assertTrue(treeSet.add(cata));
        assertTrue(treeSet.add(cata2));
    }

    @Test
    public void treeset_test_add_persons_with_same_age_different_name() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana cata2 = new Somer("Catalin2", "Popescu", "M", 39, "Demisie proprie");

        assertTrue(treeSet.add(cata));
        assertTrue(treeSet.add(cata2));
    }

    @Test
    public void treeset_test_order_by_name() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana cata2 = new Somer("Batalin", "Popescu", "M", 39, "Demisie proprie");

        treeSet.add(cata);
        treeSet.add(cata2);

        assertEquals(treeSet.getFirstElement(), cata2);
    }

    @Test
    public void treeset_test_order_by_age_when_name_identical() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana cata2 = new Somer("Catalin", "Popescu", "M", 29, "Demisie proprie");

        treeSet.add(cata);
        treeSet.add(cata2);

        assertEquals(treeSet.getFirstElement(), cata2);
    }

    @Test
    public void treeset_test_order_by_name_using_other_persons() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana radu = new Student("Radu", "Bradu", "M", 39);

        treeSet.add(radu); //first add radu
        treeSet.add(cata); //then add cata

        assertEquals(treeSet.getFirstElement(), cata);
    }

    /**
     * HashMap Tests
     */
    @Test
    public void hashmap_test_can_add_persons() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana radu = new Student("Radu", "Bradu", "M", 39);

        hashMap.add(cata, skiing);
        hashMap.add(radu, racing);

        assertTrue(hashMap.contains(cata));
        assertTrue(hashMap.contains(radu));
    }

    @Test
    public void hashmap_test_can_add_multiple_hobbies() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");

        hashMap.add(cata, skiing);
        hashMap.add(cata, racing);

        List<Hobby> hobbies = new LinkedList<Hobby>();
        hobbies.add(skiing);
        hobbies.add(racing);

        assertEquals(hashMap.get(cata), hobbies);
    }

    @Test
    public void hashmap_test_for_duplicate_hobbies_for_same_person() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");

        hashMap.add(cata, skiing);
        hashMap.add(cata, skiing);
        hashMap.add(cata, racing);
        hashMap.add(cata, racing);

        List<Hobby> hobbies = new LinkedList<Hobby>();
        hobbies.add(skiing);
        hobbies.add(racing);

        assertEquals(hashMap.get(cata), hobbies);
    }

    @Test
    public void hashmap_test_duplicate_person_has_same_key() {
        Persoana cata = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");
        Persoana cata2 = new Somer("Catalin", "Popescu", "M", 39, "Demisie proprie");

        hashMap.add(cata, skiing);
        hashMap.add(cata2, racing);

        List<Hobby> hobbies = new LinkedList<Hobby>();
        hobbies.add(skiing);
        hobbies.add(racing);

        assertEquals(hashMap.get(cata), hobbies);
    }
}