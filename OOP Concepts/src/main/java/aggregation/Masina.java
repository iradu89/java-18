package aggregation;

public class Masina {
    private String nume;
    private String culoare;
    private Motor motor;

    public Masina(Motor motor, String nume) {
        this.motor = motor;
        this.nume = nume;
        this.culoare = "alb";
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    @Override
    public String toString() {
        return "Masina " + this.getNume() + " area culoarea " + this.getCuloare()
                + " si motorul de putere " + this.getMotor().getPutere()
                + " si capacitatea: " + this.getMotor().getCapacitate();
    }
}
