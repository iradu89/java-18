public class Dreptunghi {
    private double lungime;
    private double latime;

    public Dreptunghi(double lungime, double latime) {
        this.lungime = lungime;
        this.latime = latime;
    }

    public double getAria() {
        return lungime * latime;
    }

    public double getPerimeter() {
        return 2 * (lungime + latime);
    }

    public double getDiag() {
        return Math.sqrt(lungime * lungime + latime * latime);
    }
}
