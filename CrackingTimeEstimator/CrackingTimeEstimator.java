package CrackingTimeEstimator;
import java.io.BufferedReader; // Imports the BufferedReader class from the java.io package
import java.io.FileReader; // Imports the FileReader class from the java.io package
import java.io.IOException; // Imports the IOException class from the java.io package
import java.math.BigInteger; // Imports the BigInteger class from the java.math package
import java.util.Scanner; // Imports the Scanner class from the java.util
public class CrackingTimeEstimator {
    private static final int OPERATIONS_PER_SECOND = 10000000; // Defines a constant integer called OPERATIONS_PER_SECOND and sets it to 10000000
    private static final double TIME_FOR_COMMON_PASSWORD = 0.1; // Defines a constant double called TIME_FOR_COMMON_PASSWORD and sets it to 0.1

    public static void main(String[] args) throws IOException { // Defines a public static void method called main that takes a String array called args as a parameter and throws an IOException
        Scanner in = new Scanner(System.in);
        String password = in.nextLine(); // Declares and initializes a String variable called password with the value "123456789"

        String commonPasswordsFilePath = "C:\\common-passwords.txt"; // Declares and initializes a String variable called commonPasswordsFilePath with the value "C:\\common-passwords.txt"

        boolean isPasswordCommon = false; // Declares and initializes a boolean variable called isPasswordCommon with the value false

        // Check if password is in common-passwords file
        try (BufferedReader br = new BufferedReader(new FileReader(commonPasswordsFilePath))) { // Defines a try block that creates a new BufferedReader and a new FileReader object to read the file at commonPasswordsFilePath
            String line; // Declares a String variable called line
            while ((line = br.readLine()) != null) { // Defines a while loop that reads each line of the file until the end of the file is reached
                if (line.equals(password)) { // Defines an if statement that checks if the current line is equal to the password variable
                    isPasswordCommon = true; // Sets the isPasswordCommon variable to true if the password is found in the file
                    break; // Exits the loop
                }
            }
        }

        BigInteger rank = getLexicographicRank(password); // Declares a BigInteger variable called rank and initializes it to the value returned by the getLexicographicRank method with the password variable as a parameter

        double timeInSeconds = isPasswordCommon ? TIME_FOR_COMMON_PASSWORD : getTimeToCrack(rank, password.length()); // Declares a double variable called timeInSeconds and initializes it to the value returned by either the TIME_FOR_COMMON_PASSWORD constant or the getTimeToCrack method, depending on whether the password is common or not

        printTime(timeInSeconds); // Calls the printTime method with the timeInSeconds variable as a parameter
    }

    private static BigInteger getLexicographicRank(String password) {
    BigInteger rank = BigInteger.ZERO; // Initialize rank to zero

    char[] passwordArray = password.toCharArray(); // Convert password to a character array
    int length = passwordArray.length; // Get length of the password
    int[] characterCount = new int[256]; // Initialize an array to count the frequency of characters

    // Count frequency of characters
    for (char c : passwordArray) {
        characterCount[c]++; // Increment the count for the character
    }

    // Calculate the rank
    for (int i = 0; i < length; i++) {
        // Count the number of characters smaller than password[i] that can be placed at position i
        int smallerCharCount = 0;
        for (int j = 0; j < passwordArray[i]; j++) {
            if (characterCount[j] > 0) {
                smallerCharCount++; // Increment count if character can be used
            }
        }

        // Update the rank
        rank = rank.add(BigInteger.valueOf(smallerCharCount)
                .multiply(factorial(length - i - 1))); //updates the value of a BigInteger variable named "rank" by adding the result of multiplying BigInteger.valueOf(smallerCharCount) and the factorial of (length - i - 1) to its current value.

        // Decrement the count of the character used
        characterCount[passwordArray[i]]--;
    }

    return rank; // Return the final rank
}

    // Method to calculate the factorial of a number
    private static BigInteger factorial(int n) {
        BigInteger fact = BigInteger.ONE;
        // Calculate factorial using a loop
        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i)); //updates the value of a BigInteger variable named "fact" by multiplying its current value with BigInteger.valueOf(i).
        }

        return fact;
    }
    // Method to calculate the time to crack a password using its lexicographic rank
    private static double getTimeToCrack(BigInteger rank, int passwordLength) {
        // Initialize sum to 0
        BigInteger sum = BigInteger.ZERO;
        // Calculate sum of products of length i and number of possible characters (72)
        for (int i = 1; i < passwordLength; i++) {
            BigInteger product = BigInteger.valueOf(i).multiply(BigInteger.valueOf(72).pow(i)); //This line of code initializes a BigInteger variable named "product" with the result of multiplying BigInteger.valueOf(i) by BigInteger.valueOf(72) raised to the power of i
            sum = sum.add(product);
        }
        // Add the product of password length and lexicographic rank to the sum
        BigInteger total = sum.add(BigInteger.valueOf(passwordLength).multiply(rank));
        // Calculate the time required to crack the password
        double timeInSeconds = total.divide(BigInteger.valueOf(OPERATIONS_PER_SECOND)).doubleValue();
        
        return timeInSeconds;
    }
    // Method to return time in minutes, hours, days, weeks, months, years or centuries
    private static void printTime(double timeInSeconds) {
    final double SECONDS_PER_MINUTE = 60;
    final double MINUTES_PER_HOUR = 60;
    final double HOURS_PER_DAY = 24;
    final double DAYS_PER_WEEK = 7;
    final double DAYS_PER_MONTH = 30.44;
    final double MONTHS_PER_YEAR = 12;
    final double YEARS_PER_CENTURY = 100;
    
    if (timeInSeconds < SECONDS_PER_MINUTE) {
        System.out.printf("Predicted time to crack password: %.2f seconds", timeInSeconds);
    } else if (timeInSeconds < SECONDS_PER_MINUTE * MINUTES_PER_HOUR) {
        double minutes = timeInSeconds / SECONDS_PER_MINUTE;
        System.out.printf("Predicted time to crack password: %.2f minutes", minutes);
    } else if (timeInSeconds < SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY) {
        double hours = timeInSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR);
        System.out.printf("Predicted time to crack password: %.2f hours", hours);
    } else if (timeInSeconds < SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_WEEK) {
        double days = timeInSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY);
        System.out.printf("Predicted time to crack password: %.2f days", days);
    } else if (timeInSeconds < SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_MONTH * MONTHS_PER_YEAR) {
        double weeks = timeInSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_WEEK);
        System.out.printf("Predicted time to crack password: %.2f weeks", weeks);
    } else if (timeInSeconds < SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_MONTH * MONTHS_PER_YEAR * YEARS_PER_CENTURY) {
        double months = timeInSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_MONTH);
        System.out.printf("Predicted time to crack password: %.2f months", months);
    } else {
        double centuries = timeInSeconds / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR * HOURS_PER_DAY * DAYS_PER_MONTH * MONTHS_PER_YEAR * YEARS_PER_CENTURY);
        System.out.printf("Predicted time to crack password: %.2f centuries", centuries);
    }
  }
}
