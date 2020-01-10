package composition;

public class MainCladire {
    public static void main(String[] args) {
        Cladire cladireA = new Cladire("CBC");
        cladireA.setMarimeCamera(100);
        cladireA.setCuloareCamera("rosu");
        System.out.println(cladireA.getNume() + " are o camera de culoare " + cladireA.getCamera().getCuloare());
    }
}
