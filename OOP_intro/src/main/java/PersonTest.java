public class PersonTest {
    public static void main(String[] args) {
        Person persoana1 = new Person();
        System.out.println(persoana1.name);

        Person persoana2 = new Person("Bogdan");
        System.out.println(persoana2.name);

        persoana1.setName("Ana");
        System.out.println("Noul nume al obiectului persoana1 este: " + persoana1.name);
    }
}
