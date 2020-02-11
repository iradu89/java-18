public class MainDreptunghi {
    public static void main(String[] args) {
        Dreptunghi drept1 = new Dreptunghi(21.0, 30.0);
        Dreptunghi drept2 = new Dreptunghi(15.5, 7.5);

        System.out.println("Aria drept1 este: " + drept1.getAria());
        System.out.println("Perim: " + drept1.getPerimeter());
        System.out.println("Diagonala e: " + drept1.getDiag());

        System.out.println("Aria drept2 este: " + drept2.getAria());
        System.out.println("Perim: " + drept2.getPerimeter());
        System.out.println("Diagonala e: " + drept2.getDiag());
    }
}
