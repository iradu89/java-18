package phones.behaviour;

/**
    This is used to create a random IMEI for each phone instead of manually writing one.
    Temp solution, testing for duplicates NOT IMPLEMENTED
 */
public class Imei {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric() {
        //IMEI is of 15 chars
        int count = 15;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
