package PasswordGenerator1;
import java.util.Random;
import java.util.Scanner;
public class PasswordGenerator {
    static String generatePassword(int size)
    {
    // Setting the characters to output password from it
        String alphanum = "0123456789!@#$%^&*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";
        // initializing a new Random class
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            // Choosing a random index from alphanum
            int index = random.nextInt(alphanum.length());
            // Adding the char from index
            result += alphanum.charAt(index);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int userInput = in.nextInt();
        System.out.println(generatePassword(userInput));
    }
    
}