package day2;

import general.Helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day02 {
    // Maximum possible balls for exercise 1.
    private static final Map<String, Integer> maxBalls = Map.of(
            "red",      12,
            "green",    13,
            "blue",     14
    );
    // Smallest minimum for exercise 2.
    private static final Map<String, Integer> defaultMinBalls = Map.of(
            "red",      0,
            "green",    0,
            "blue",     0
    );

    // Whether to execute for exercise 2.
    private static final boolean isExtension = true;

    public static void main(String[] args) throws IOException {
        // Get path (default to file in repo)
        String path = Helper.getUserDir() + "/day2/input.txt";
        if (args.length != 0) {
            path = args[0];
        }
        // Print total
        System.out.println(calculateTotal(Helper.readFile(path)));
    }

    private static int calculateTotal(String contents) {
        // Get lines
        String[] lines = contents.split("\n");

        // Iterate over lines
        int idx = 1;
        int total = 0;
        for (String line : lines) {

            // Skip blank lines
            if (line.isBlank()) {
                idx++;
                continue;
            }
            // Exercise 1: determine if a game is possible; add index if so
            if (!isExtension && gameIsPossible(line.split(":")[1].strip())) {
                total += idx;
                idx++;
            }
            // Exercise 2: determine permutation; add to total
            if (isExtension) {
                Map<String, Integer> minBalls = getMinBalls(line.split(":")[1].strip());
                total += minBalls.get("red") * minBalls.get("green") * minBalls.get("blue");
            }
        }
        return total;
    }

    private static boolean gameIsPossible(String game) {
        // Parse each round
        for (String round : game.split(";")) {

            // Parse each draw
            for (String draw : round.split(",")) {

                // Get number and color
                String[] drawSplit = draw.strip().split(" ");
                int num = Integer.parseInt(drawSplit[0].strip());
                String color = drawSplit[1].strip();

                // determine whether the draw is possible
                if (!drawIsPossible(num, color)) {
                    return false;
                }
            }
        }
        // If all pass, succeed
        return true;
    }

    private static boolean drawIsPossible(int num, String color) {
        return num <= maxBalls.get(color);
    }

    private static Map<String, Integer> getMinBalls(String game) {
        Map<String, Integer> minBalls = new HashMap<>(defaultMinBalls);

        // Parse each round
        for (String round : game.split(";")) {

            // Parse each draw
            for (String draw : round.split(",")) {

                // Get number and color
                String[] drawSplit = draw.strip().split(" ");
                int num = Integer.parseInt(drawSplit[0].strip());
                String color = drawSplit[1].strip();

                // Update minimum possible balls
                updateMinBalls(minBalls, num, color);
            }
        }
        return minBalls;
    }

    private static void updateMinBalls(Map<String, Integer> minBalls, int num, String color) {
        // Set min to minimum necessary balls
        minBalls.put(color, Integer.max(num, minBalls.get(color)));
    }
}
