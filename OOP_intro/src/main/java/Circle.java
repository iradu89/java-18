public class Circle {
    //variabile de instanta
    public static final double PI = 3.14;
    double radius;
    String color;

    //constructor
    public Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    //metode
    public double getAria() {
        return PI * this.radius * this.radius;
    }

    public double getPerimeter() {
        return 2 * PI * this.radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", color='" + color + '\'' +
                '}';
    }
}
