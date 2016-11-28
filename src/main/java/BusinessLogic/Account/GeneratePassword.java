package BusinessLogic.Account;

import java.util.Random;

/**
 * Created by ${Boris} Grunwald} on 27/11/2016.
 */
public class GeneratePassword {

    public static String generatePassword() {

        String symbols = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
        String password = "";
        Random r = new Random();

        for (int i = 0; i <= 12; i++) {
            password += symbols.charAt(r.nextInt(symbols.length()));
        }

        return password;
    }

}
