package javaGr18;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class JavaOperations {
    private static final Logger LOGGER = Logger.getLogger(JavaOperations.class.getName());

    /**
     * Create a Person / Car custom object of choice with any number of fields (> 2) of different types.
     * 1) Write 5 different instances of that object.
     * Add the instances to a list.
     */
    public static List<Person> populateList() {
        System.out.println("----------Exercise 1----------");
        List<Person> result = new ArrayList<>();

        result.add(new Person("Radu Constantin", 30, 85, 1.76));
        result.add(new Person("Mugur Popi", 9, 58, 1.60));
        result.add(new Person("Melania Popescu", 28, 78, 1.80));
        result.add(new Person("Tudor Budor", 29, 88, 1.72));
        result.add(new Person("Mihaela Prisaca", 26, 55, 1.64));

        return result;
    }

    /**
     * 2) Find the elements containing the letter "a" that start with "M" and print them out.
     * Add the instances to a set.
     */
    public static Set<Person> findElementsAndPrintAndReturnSet(List<Person> input) {
        System.out.println("----------Exercise 2----------");
        Set<Person> personSet = input.stream()
                .filter(person -> person.getName().startsWith("M"))
                .filter(person -> person.getName().contains("a"))
                .collect(Collectors.toSet());

        personSet.forEach(System.out::println);
        return personSet;
    }

    /**
     * 3) Find the "min" using a custom comparing criteria of choice.
     */
    public static Person getYoungestPerson(List<Person> input) {
        System.out.println("----------Exercise 3----------");
        Optional<Person> person = input.stream()
                .min(Comparator.comparing(Person::getAge));

        //logging error, otherwise could have use orElse(null);
        if (person.isPresent()) {
            return person.get();
        } else {
            LOGGER.warning("Method returned a null because it couldn't find the youngest person (list probably empty)");
            return null;
        }
    }

    /**
     * 4) Generate 5 random Strings and add them to a Set
     */
    public static Set<String> populateWithRandoms(int howMany) {
        System.out.println("----------Exercise 4----------");
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < howMany; i++) {
            stringSet.add(RandomBuilder.buildString());
        }
        return stringSet;
    }

    /**
     * 4) Find the "max"(while explaining as Javadoc how comparing Strings works)
     * <p>
     * Comparing strings : compared in alphabetical order, most important is the first char,
     * as it is with words compared. Because we are looking for max the result is actually in reverse order.
     * Chars are compared using their ASCII values, this means lowercase chars have a higher value than uppercase ones.
     * for example a is 97 and A is 65. Then we compare the values. a is greater than A because 97 > 65
     * So "apple" is greater than "Apple", also length matters only if the first char/s is/are the same
     * meaning "apple" is greater than "bppleapple", but "apple" is smaller than "abple"
     */
    public static String findMax(Set<String> input) {
        Optional<String> max = input.stream()
                .max((String::compareTo));

        //logging error, otherwise could have use orElse(null);
        if (max.isPresent()) {
            return max.get();
        } else {
            LOGGER.warning("Method returned a null because it couldn't find the max (set probably empty)");
            return null;
        }
    }

    /**
     * 5) Generate a random number of integers and count them. "Map" the exponential to the numbers and then print them out.
     */
    public static void randomNumbersExponentialPrint(int bound) {
        System.out.println("----------Exercise 5----------");
        //generating a list of random numbers between 1 and 20 with the bound set by the user.
        //only chose between 1 and 20 because the numbers could get too big in the following exercises
        List<Integer> randomInts = new Random().ints(new Random().nextInt(bound),
                1, 20)
                .boxed()
                .collect(Collectors.toList());

        //counts the ints. Could also use stream().count() but the result is the same
        System.out.println("Number of integers: " + randomInts.size());

        //using Math.exp(). Could have used x -> Math.pow(x, x) for a different result.
        randomInts.stream().mapToDouble(Math::exp).forEach(System.out::println);
    }

    /**
     * 6) Create a map of "n" (K,V) elements and print "how many" elements have value over 10 (the key is of your choice)
     */
    public static Map<String, Integer> createMapAndPrintHowManyElementsWithGivenValue(List<Person> personList, int value) {
        System.out.println("----------Exercise 6----------");
        Map<String, Integer> result = personList.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));

        System.out.println("Number of elements with value under " + value + " is: "
                + result.values().stream().filter(x -> x < value).count());

        return result;
    }

    /**
     * 7) Sort the above Set<String> (used for max) in reverse order
     */
    public static Set<String> sortSetInReverseOrder(Set<String> input) {
        System.out.println("----------Exercise 7----------");
        return new TreeSet<>(input).descendingSet();
    }

    /**
     * 8) Sort the above List of custom objects (used for filter) in an order you consider
     */
    public static List<Person> sortListInCustomOrder(List<Person> personList) {
        System.out.println("----------Exercise 8----------");
        return personList.stream()
                .sorted(Comparator.comparing(Person::getWeight))
                .collect(Collectors.toList());
    }

    /**
     * 9) Check if any of your instances "match" a condition based on an Integer field (of your choice). What does it return ? Print it out.
     */
    public static boolean ageExistsInList(List<Person> personList, int age) {
        System.out.println("----------Exercise 9----------");
        return personList.stream().anyMatch(Person -> Person.getAge() == age);
    }

    /**
     * 10) What does Optional represent ? Explain through an example on your custom object
     * <p>
     * -> Wrap an existing instance
     * -> Wrap a null
     * -> Check value using ifPresent or isPresent
     * <p>
     * Optionals represent an easier and less messy way to deal with null pointer exceptions that can appear at runtime.
     * It is a class that encapsulates an optional value.
     * It is used when we expect that a value may or may not be there, thus we make it Optional.
     */
    public static void optionalExample(Person person, Set<String> emptySet) {
        System.out.println("----------Exercise 10----------");
        //Wrapped an existing instance
        Optional<Person> maybePerson = Optional.of(person);

        //Wrapped a null
        Optional<String> max = emptySet.stream()
                .max((String::compareTo));

        //Checking using ifPresent
        maybePerson.ifPresent(System.out::println);

        //Checking using isPresent
        if (max.isPresent()) {
            System.out.println("The string isn't null");
        } else System.out.println("The string is null");
    }

    /**
     * 11) Fastest way to find the shortest String in a List
     * (take the 5 random Strings generated above and find the shortest one).
     */
    public static String findShortestString(Set<String> input) {
        System.out.println("----------Exercise 11----------");
        long startTime; //using nanoTime() because it is more precise
        long endTime; //using nanoTime() because it is more precise

        //After some testing this is the optimal solution
        //Classic way is fastest for size under 100
        if (input.size() < 100) {
            startTime = System.nanoTime();
            String resultClassic = input.iterator().next();
            for (String string : input) {
                if (resultClassic.length() > string.length()) {
                    resultClassic = string;
                }
            }
            endTime = System.nanoTime();
            System.out.println("Classic way took " + (1.0 * (endTime - startTime) / 1000000) + " milliseconds");
            return resultClassic;

            //Reducer is fastest for size under 500000
        } else if (input.size() < 500000) {
            startTime = System.nanoTime();
            String resultReducer = input.stream()
                    .reduce((word1, word2) ->
                            word1.length() < word2.length() ? word1 : word2)
                    .orElseThrow();
            endTime = System.nanoTime();
            System.out.println("Stream reducer took " + (1.0 * (endTime - startTime) / 1000000) + " milliseconds");
            return resultReducer;

            //Parallel reducer is fastest for size over 500000
        } else {
            startTime = System.nanoTime();
            String resultParallelReducer = input.stream()
                    .parallel()
                    .reduce((word1, word2) ->
                            word1.length() < word2.length() ? word1 : word2)
                    .orElseThrow();
            endTime = System.nanoTime();
            System.out.println("Stream parallel reducer took " + (1.0 * (endTime - startTime) / 1000000) + " milliseconds ");
            return resultParallelReducer;
        }
    }
}
