import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Formatter;
import java.io.PrintWriter;
import java.io.IOException;

public class RegexAndFormattingDemo {

    public static void main(String[] args) {
        // Using Scanner class for input
        Scanner scanner = new Scanner(System.in);

        // Step 1: Input a string to manipulate
        System.out.println("Enter a sentence:");
        String inputString = scanner.nextLine();

        // Step 2: Using Pattern and Matcher to find words with digits
        Pattern pattern = Pattern.compile("\\w+\\d+\\w*");
        Matcher matcher = pattern.matcher(inputString);

        System.out.println("Words with digits:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        // Step 3: Use String.split to break the sentence into words
        String[] words = inputString.split("\\s+");
        System.out.println("Words split by spaces:");
        for (String word : words) {
            System.out.println(word);
        }

        // Step 4: Demonstrating formatting with PrintWriter and Formatter
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            Formatter formatter = new Formatter(writer);
            formatter.format("Total words: %d%n", words.length);
            for (String word : words) {
                formatter.format("Word: %s, Length: %d%n", word, word.length());
            }
            formatter.close();
            System.out.println("Formatted output written to output.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // Close the scanner
        scanner.close();
    }
}
