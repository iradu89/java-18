package javaGr18;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        //1.
        List<Person> personList = JavaOperations.populateList();

        //2.
        Set<Person> personSet = JavaOperations.findElementsAndPrintAndReturnSet(personList);

        //3
        Person youngestPerson = JavaOperations.getYoungestPerson(personList);

        //4
        Set<String> randomStringSet = JavaOperations.populateWithRandoms(5);
        String max = JavaOperations.findMax(randomStringSet);

        //5
        //using a lower bound because we have to print the exponential to the numbers in the console.
        JavaOperations.randomNumbersExponentialPrint(1000);

        //6
        Map<String, Integer> personMap = JavaOperations.createMapAndPrintHowManyElementsWithGivenValue(personList, 10);

        //7
        randomStringSet = JavaOperations.sortSetInReverseOrder(randomStringSet);

        //8
        personList = JavaOperations.sortListInCustomOrder(personList);

        //9
        System.out.println("Is anybody in the list 26 years old?");
        System.out.println(JavaOperations.ageExistsInList(personList, 26)); // true
        System.out.println("Is anybody in the list 27 years old?");
        System.out.println(JavaOperations.ageExistsInList(personList, 27)); // false

        //10
        JavaOperations.optionalExample(youngestPerson, new HashSet<>());

        //11
        String shortestString = JavaOperations.findShortestString(randomStringSet);
    }
}
