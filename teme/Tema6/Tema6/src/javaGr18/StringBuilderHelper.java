package javaGr18;

class StringBuilderHelper {
    private static final String ALPHABET_LOWER_UPPER = "abcdefghijklmnoprstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String buildString() {
        //max 30 chars
        int count = (int) (30 * Math.random()) + 1;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHABET_LOWER_UPPER.length());
            builder.append(ALPHABET_LOWER_UPPER.charAt(character));
        }
        return builder.toString();
    }
}
