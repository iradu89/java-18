package phones.apple;

import phones.behaviour.Imei;

public class Iphone11 extends Apple {

    //Every Iphone11 has the same battery life which cannot change as per specifications
    private static final int BATTERY_LIFE = 26;   //26 hours battery

    public Iphone11() {
        super("ivory", "titanium body", Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }

    //Configurable color & material properties as per spec
    public Iphone11(String color, String material) {
        super(color, material, Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }
}
