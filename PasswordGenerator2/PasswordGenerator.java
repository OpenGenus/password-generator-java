package PasswordGenerator2;
import org.apache.commons.lang3.RandomStringUtils; // Import the RandomStringUtils
import java.util.Scanner;
public class PasswordGenerator {
    static String generatePassword(int size)
    {
        String password = RandomStringUtils.random(size, true, true); // Generate a random password of length size
        System.out.println("Generated Password: " + password);
        return password;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int userInput = in.nextInt();
        System.out.println(generatePassword(userInput));
    }
}
