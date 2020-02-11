package phones.apple;

import phones.behaviour.Imei;


public class IphoneX extends Apple {

    //Every IphoneX has the same battery life which cannot change as per specifications
    private static final int BATTERY_LIFE = 20;   //20 hours battery

    public IphoneX() {
        super("gold", "aluminum alloy body", Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }

    //Configurable color & material properties as per spec
    public IphoneX(String color, String material) {
        super(color, material, Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }
}
