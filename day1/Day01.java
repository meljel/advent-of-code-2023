package day1;

import general.Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Day01 {
    // Digit strings
    private final static Map<String, Integer> digits = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);

    // Is it the second exercise?
    private static final boolean isExtended = true;

    public static void main(String[] args) throws IOException {
        // Get path (default to file in repo)
        String path = Helper.getUserDir() + "/day1/input.txt";
        if (args.length != 0) {
            path = args[0];
        }
        // Print total
        System.out.println(calculateTotal(path));
    }

    private static int calculateTotal(String path) throws IOException {
        // Get reader for input file
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int leftSum = 0;
        int rightSum = 0;

        // Read from input file
        String line;
        while ((line = reader.readLine()) != null) {

            // Only consider digits
            String nums = line.replaceAll("[^0-9]", "");

            // Throw exception for files with invalid lines
            if (nums.isEmpty()) {
                throw new IOException("A line does not any digits: " + line);
            }
            // First exercise: get first and last ints
            int leftMost = Integer.parseInt(String.valueOf(nums.charAt(0)));
            int rightMost = Integer.parseInt(String.valueOf(nums.charAt(nums.length() - 1)));

            // Second exercise: get first and last ints OR number strings
            if (isExtended) {
                leftMost = getFirstNumber(line, leftMost);
                rightMost = getLastNumber(line, rightMost);
            }
            // Add to left, right sums
            leftSum += leftMost;
            rightSum += rightMost;
        }
        // Close reader
        reader.close();

        // Return sum
        return 10 * leftSum + rightSum;
    }

    private static int getFirstNumber(String line, int firstInt) {
        // Initialize for argmin
        int minIdx = line.length();
        int value = -1;

        // Iterate over digit strings
        for (String digit : digits.keySet()) {

            // Get index
            int idx = line.indexOf(digit);

            // Update min and argmin
            if (idx < minIdx && idx != -1) {
                minIdx = idx;
                value = digits.get(digit);
            }
        }
        // Return number/string with smaller index
        return (minIdx < line.indexOf(String.valueOf(firstInt)) && value != -1) ? value : firstInt;
    }

    private static int getLastNumber(String line, int lastInt) {
        // Initialize for argmin
        int maxIdx = -1;
        int value = -1;

        // Iterate over digit strings
        for (String digit : digits.keySet()) {

            // Get index
            int idx = line.lastIndexOf(digit);

            // Update max and argmax
            if (idx > maxIdx) {
                maxIdx = idx;
                value = digits.get(digit);
            }
        }
        // Return number/string with larger index
        return (maxIdx > line.lastIndexOf(String.valueOf(lastInt)) && value != -1) ? value : lastInt;
    }
}