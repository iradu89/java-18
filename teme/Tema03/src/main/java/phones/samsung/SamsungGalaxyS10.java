package phones.samsung;

import phones.behaviour.Imei;

public class SamsungGalaxyS10 extends Samsung{
    //Every GalaxyS10 has the same battery life which cannot change as per specifications
    private static final int BATTERY_LIFE = 24;   //24 hours battery

    public SamsungGalaxyS10() {
        super("blue", "titanium", Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }

    //Configurable color & material properties as per spec
    public SamsungGalaxyS10(String color, String material) {
        super(color, material, Imei.randomAlphaNumeric(), BATTERY_LIFE);
    }
}
