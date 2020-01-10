package aggregation;

public class Motor {
    private int putere;
    private double capacitate;

    public Motor() {
        this.putere = 75;
        this.capacitate = 0.8;
    }

    public int getPutere() {
        return putere;
    }

    public double getCapacitate() {
        return capacitate;
    }

    public void setPutere(int putere) {
        if (putere < 75) {
            System.out.println("Nu puteti introduce o valoare mai mica decat 75 pt putere");
        } else {
            this.putere = putere;
        }
    }

    public void setCapacitate(double capacitate) {
        if (capacitate < 0.8) {
            System.out.println("Nu puteti introduce o valoare mai mica decat 0.8 pt capacitate");
        } else {
            this.capacitate = capacitate;
        }
    }
}
