package phones.huawei;

import phones.behaviour.Imei;

public class P30Pro extends Huawei {
    //Every p30Pro has the same battery life which cannot change as per specifications
    private static final int BATTERY_LIFE = 48;   //48 hours battery

    public P30Pro() {
        super("orange", "plastic", Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }

    //Configurable color & material properties as per spec
    public P30Pro(String color, String material){
        super(color, material, Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }
}
