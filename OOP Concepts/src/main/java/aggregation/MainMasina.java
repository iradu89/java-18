package aggregation;

public class MainMasina {
    public static void main(String[] args) {
        //masina VW de culoare alba cu motor capacitate 1.0 si putere 75
        //afisam MSG: Masina X are culoarea Y si motorul de putere Z si capacitate T
        Motor motorVW = new Motor();
        motorVW.setCapacitate(1);
        motorVW.setPutere(75);

        System.out.println("MotorVW are puterea: " + motorVW.getPutere());
        System.out.println("MotorVW are capacitatea: " + motorVW.getCapacitate());

        Masina masinaVW = new Masina(motorVW, "Volkswagen");
        masinaVW.setCuloare("alb");

        System.out.println(masinaVW);

        //diferenta dintre aggregation si composition. Daca clasa interioara cum aici e Motor moare o data cu Masina, atunci e composition. Daca nu, e aggregation.
        //AICI E AGGREGATION ca motorul inca exista
    }
}
