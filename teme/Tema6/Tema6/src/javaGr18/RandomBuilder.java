package javaGr18;

class RandomBuilder {
    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnoprstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String buildString() {
        //max 30 chars
        int count = (int) (30 * Math.random()) + 1;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
