package util;

import java.util.Random;

public class AccountNumberGenerator {

    // Static method to generate a unique account number
    public static String generateAccountNumber(String name) {
        Random rand = new Random();

        // Extract initials (first two letters of name)
        String initials = "";
        String[] parts = name.trim().split(" ");
        for (String part : parts) {
            initials += Character.toUpperCase(part.charAt(0));
        }

        // Add 4-digit random number
        int randomNum = 1000 + rand.nextInt(9000);

        // Combine initials + random number
        return initials + randomNum;
    }
}
