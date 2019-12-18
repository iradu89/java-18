package calculator;

public class CalculatorSimplu {
    public static void main(String[] args) {
        //args care va contine 3 elem.  1 * 2
        if (args.length < 3) {
            System.out.println("trebuie sa avem sintaxa ex: 1 * 2");
        } else {
            int numar1 = Integer.parseInt(args[0]);
            int numar2 = Integer.parseInt(args[2]);
            String operator = args[1];
            calculeaza(numar1, numar2, operator);
        }

    }

    /** Metoda care printeaza o operatie intre 2 numere.
     *
     * @param numar1 var de tip int
     * @param numar2 var de tip int
     * @param operator care poate fi *, /, + , -
     */

    public static void calculeaza(int numar1, int numar2, String operator) {
        switch (operator.charAt(0)) {
            case '+':
                System.out.println(numar1 + numar2);
                break;
            case '-':
                System.out.println(numar1 - numar2);
                break;
            case '*':
                System.out.println(numar1 * numar2);
                break;
            case '/':
                System.out.println(numar1 / numar2);
                break;
            default:
                System.out.println("mai incercati");
        }
    }
}
