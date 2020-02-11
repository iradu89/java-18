package phones.samsung;

import phones.behaviour.Imei;


public class SamsungGalaxyS6 extends Samsung {
    //Every GalaxyS6 has the same battery life which cannot change as per specifications
    private static final int BATTERY_LIFE = 16;   //16 hours battery

    public SamsungGalaxyS6() {
        super("white", "aluminum frame", Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }

    //Configurable color & material properties as per spec
    public SamsungGalaxyS6(String color, String material) {
        super(color, material, Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }

}
