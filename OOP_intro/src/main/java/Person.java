/**
 * Class Person
 */
public class Person {
    public static final double PI = 3.14;
    String name; //attribute or instance variable
    int age;

    //constructor implicit
    public Person() {
        System.out.println("Constructorul implicit a fost apelat");
        this.name = "no name";
        this.age = 0; //oricum e initializat by default cu 0 deci e redundant sa scrii = 0;
    }

    //constructor explicit
    public Person(String name) {
        System.out.println("Constructorul explicit cu 1 parametru a fost apelat");
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
