package loopexamples;

public class Main {
    public static void main(String[] args) {
        int[] arrayInt = {10, 20, 30, 40, 50};
        afisareElemArray(arrayInt);

        //calculeaza suma in functie de niste conditii
        //a) daca elem din array == 30 nu se va calcula suma
        //b) daca suma depaseste val 100 se intrerupe executia array-ului

        sumaNrArray(arrayInt);
        int numar = 1501;

        //o noua linie de cod
        //O metoda de inversare folosint % si /
        inversareNumar(numar);

        System.out.println();
        //O alta metoda de inversare convertind la String
        inversareNumer2(numar);

        String propozitie = "Ana are mere";
        afisareNrVocale(propozitie);
    }

    private static void afisareNrVocale(String propozitie) {
        char[] arrayCaractere = propozitie.toCharArray();

        int nrVocale = 0;
        for (char element : arrayCaractere) {
            System.out.println(element);

            if(element == 'a' || element == 'A' || element == 'e' || element == 'i' || element == 'o' || element == 'u'){
                nrVocale++;
            }
        }
        System.out.println("Nr vocale din string e: " + nrVocale);
    }

    private static void inversareNumar(int numar) {
        int numarInversat = 0;
        while (numar > 0) {
            numarInversat = numarInversat * 10 + numar % 10;
            numar = numar / 10;
        }
        System.out.println("Numarul inversat este: " + numarInversat);
    }

    private static void inversareNumer2(int numar) {
        char arrayChar[] = String.valueOf(numar).toCharArray();

        System.out.print("Numarul inversat cu metoda 2 este: ");
        for (int i = arrayChar.length - 1; i >= 0; i--) {
            System.out.print(arrayChar[i]);
        }
        System.out.println();
    }

    private static void afisareElemArray(int[] arrayInt) {
        //afisare elem array
        for (int elem : arrayInt) {
            System.out.println(elem);
        }
    }

    private static void sumaNrArray(int[] arrayInt) {
        int suma = 0;
        for (int elem : arrayInt) {
            if (elem == 30) {
                continue;
            }
            if (suma > 100) {
                break;
            }
            suma += elem;
            System.out.println("elementul este: " + elem + " suma este: " + suma);
        }
        System.out.println("Am iesit din main, suma finala este: " + suma);
    }
}
