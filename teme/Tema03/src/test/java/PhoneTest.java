import org.junit.jupiter.api.Test;
import phones.behaviour.Message;
import phones.behaviour.Phone;
import phones.samsung.Samsung;
import phones.samsung.SamsungGalaxyS6;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneTest {

    @Test
    public void test_message_length_505_chars() {
        Message message = new Message("0756667889",
                randomAlphaNumeric(505, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
        assertEquals(null, message.getPhoneNum());
    }

    @Test
    public void test_message_correct_length() {
        Message message = new Message("0756667889", randomAlphaNumeric(400,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
        assertEquals("0756667889", message.getPhoneNum());
    }

    @Test
    public void test_samsungGalaxyS6_battery_limit_by_calling() {
        Phone phone = new SamsungGalaxyS6();
        for (int i = 0; i < 10; i++) {
            phone.call(randomAlphaNumeric(10, "0123456789"));
        }
        assertEquals(8, ((Samsung) phone).getCalls().size());
    }

    @Test
    public void test_samsungGalaxyS6_battery_limit_by_messaging() {
        Phone phone = new SamsungGalaxyS6();
        for (int i = 0; i < 20; i++) {
            phone.sendMessage(randomAlphaNumeric(10, "0123456789"),
                    randomAlphaNumeric(300, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
        }
        assertEquals(16, ((Samsung) phone).getMessages().size());
    }

    @Test
    public void test_samsungGalaxyS6_battery_limit_by_messaging_and_calling() {
        Phone phone = new SamsungGalaxyS6();
        for (int i = 0; i < 3; i++) {
            phone.sendMessage(randomAlphaNumeric(10, "0123456789"),
                    randomAlphaNumeric(300, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));
        }

        for (int i = 0; i < 20; i++) {
            phone.call(randomAlphaNumeric(10, "0123456789"));
        }

        assertEquals(9, ((Samsung) phone).getMessages().size() + ((Samsung) phone).getCalls().size());
    }


    //helper method for creating calls and messages
    public static String randomAlphaNumeric(int count, String alphaNumeric) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * alphaNumeric.length());
            builder.append(alphaNumeric.charAt(character));
        }
        return builder.toString();
    }
}