package _05_dynamic_programming_part_2.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Pr01ConnectingCables {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int[] unordered = Arrays
                    .stream(reader.readLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int cables = unordered.length;

            Integer[][] maxConnected = new Integer[cables][cables];

            int result = getMaxConnected(cables - 1, cables - 1, unordered, maxConnected);

            System.out.printf("Maximum pairs connected: %d%n", result);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static int getMaxConnected(final int orderedIndex, final int unorderedIndex,
                                       final int[] unordered, Integer[][] maxConnected) {
        if (orderedIndex < 0 || unorderedIndex < 0) {
            return 0;
        }

        if (maxConnected[orderedIndex][unorderedIndex] != null) {
            return maxConnected[orderedIndex][unorderedIndex];
        }

        if (unordered[unorderedIndex] == orderedIndex + 1) {
            maxConnected[orderedIndex][unorderedIndex] = 1 + getMaxConnected(
                    orderedIndex - 1, unorderedIndex - 1, unordered, maxConnected);
        } else {
            int reducedOrdered = getMaxConnected(orderedIndex - 1, unorderedIndex, unordered, maxConnected);
            int reducedUnordered = getMaxConnected(orderedIndex, unorderedIndex - 1, unordered, maxConnected);

            maxConnected[orderedIndex][unorderedIndex] = Math.max(reducedOrdered, reducedUnordered);
        }

        return maxConnected[orderedIndex][unorderedIndex];
    }
}
