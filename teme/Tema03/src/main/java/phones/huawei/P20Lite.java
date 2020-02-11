package phones.huawei;

import phones.behaviour.Imei;

public class P20Lite extends Huawei {
    //Every p30Lite has the same battery life which cannot change as per specifications
    private static final int BATTERY_LIFE = 30;   //30 hours battery

    public P20Lite() {
        super("orange", "plastic", Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }

    //Configurable color & material properties as per spec
    public P20Lite(String color, String material) {
        super(color, material, Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }
}
