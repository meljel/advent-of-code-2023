package day3;

import general.Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day03 {
    public static void main(String[] args) throws IOException {
        // Get path (default to file in repo)
        String path = Helper.getUserDir() + "/day2/input.txt";
        if (args.length != 0) {
            path = args[0];
        }
        System.out.println(calculateTotal(path));
    }

    private static int calculateTotal(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        // Consider the current line and previous line
        String currentLine = reader.readLine();
        String prevLine = ".".repeat(currentLine.length());
        while ((currentLine = reader.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < currentLine.length(); i++) {
                sb.append(getType(currentLine.toCharArray()[i], prevLine.toCharArray()[i]));
            }
            prevLine = currentLine;
        }
        return 0;
    }

    private static String getType(Character c, Character p) {
            return "";
    }
}
