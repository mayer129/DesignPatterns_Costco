package access;

import java.util.Random;

public class EmployeeIDGenerator {

    public static String generateUniqueID(String name) {
        // Ensure the name has at least 3 characters, otherwise pad with 'X'
        String namePrefix = name.length() >= 3 ? name.substring(0, 3).toUpperCase() : 
                                                String.format("%-3s", name).toUpperCase().replace(' ', 'X');
        // Generate a random 3-digit number
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(900); // Ensures a 3-digit number (100-999)

        // Combine the name prefix and the random number to form the ID
        return namePrefix + randomNumber;
    }
}
