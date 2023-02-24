package PasswordStrengthChecker;
import java.util.Scanner;
public class PasswordStrengthChecker {
    static String generatePassword(String password) {
        int length = password.length();
        // Check if the password contains at least one uppercase letter
        boolean hasUppercase = !password.equals(password.toLowerCase());
        // Check if the password contains at least one lowercase letter
        boolean hasLowercase = !password.equals(password.toUpperCase());
        // Check if the password contains at least one number
        boolean hasNumbers = password.matches(".*\\d.*");
        // Check if the password contains at least one special symbol
        boolean hasSymbols = password.matches(".*[!@#$%^&*_=+-/.?<>)].*");
    
        int strength = 0;
    
        if (length >= 8) {
            strength++;
        }
    
        if (hasUppercase && hasLowercase) {
            strength++;
        }
    
        if (hasNumbers) {
            strength++;
        }
    
        if (hasSymbols) {
            strength++;
        }
        if (strength == 1)
        {
            return "Your password is weak";
        }
        else if (strength == 2){
            return "Your password is good";
        }
        else if (strength == 3){
            return "Your password is strong";
        }
        else if (strength == 4){
            return "Your password is stronger than fort knox!!!";
        }
        return "Your password is bad";
      }
      public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            System.out.println(generatePassword(userInput));
        }
}
