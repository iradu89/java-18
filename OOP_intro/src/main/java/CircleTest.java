public class CircleTest {
    public static void main(String[] args) {
        Circle c1 = new Circle(5, "red");
        System.out.println("Aria obiectului c1 este: " + c1.getAria());
        System.out.println("Perimetrul obiectului c1 este: " + c1.getPerimeter());
        System.out.println("Culoarea obiectului c1 este: " + c1.color);

        System.out.println();
        Circle c2 = new Circle(7, "green");
        System.out.println("Aria obiectului c2 este: " + c2.getAria());
        System.out.println("Perimetrul obiectului c2 este: " + c2.getPerimeter());
        System.out.println("Culoarea obiectului c2 este: " + c2.color);

        System.out.println();
        Circle c3 = new Circle(8, "black");
        System.out.println("Aria obiectului c3 este: " + c3.getAria());
        System.out.println("Perimetrul obiectului c3 este: " + c3.getPerimeter());
        System.out.println("Culoarea obiectului c3 este: " + c3.color);

        Circle c4 = new Circle(10, "pink");
        System.out.println("APELARE METODE DIN OBJECT CLASS");
        System.out.println(c4.toString());
        System.out.println();

        createArrayOfCircle();
    }


    private static void createArrayOfCircle() {
        Circle[] circleArray = new Circle[10];
        for (int i = 0; i <= circleArray.length - 1; i++) {
            if (i % 2 == 0) {
                Circle circleEven = new Circle(i + 1, "blue");
                circleArray[i] = circleEven;
            } else {
                Circle circleOdd = new Circle(i + 1, "green");
                circleArray[i] = circleOdd;
            }
        }

        int j = 1;
        for (Circle circle : circleArray) {
            System.out.println(j + " " + circle.toString());
            j++;
        }
    }
}
