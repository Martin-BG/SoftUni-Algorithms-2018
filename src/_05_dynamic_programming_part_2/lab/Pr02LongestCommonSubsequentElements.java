package _05_dynamic_programming_part_2.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

public class Pr02LongestCommonSubsequentElements {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            char[] first = reader.readLine().trim().toCharArray();
            char[] second = reader.readLine().trim().toCharArray();

            int[][] lcs = getLcsMatrix(first, second);

            int lcsLength = lcs[first.length][second.length];
//            System.out.println(lcsLength);  // For Judge

            Iterable<Character> result = getCharSequence(first, second, lcs);
            System.out.printf("LCS = %d %s%n", lcsLength, result);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static int[][] getLcsMatrix(final char[] first, final char[] second) {
        int[][] lcs = new int[first.length + 1][second.length + 1];

        for (int row = 1; row <= first.length; row++) {
            for (int col = 1; col <= second.length; col++) {
                int up = lcs[row - 1][col];
                int left = lcs[row][col - 1];

                int best = Math.max(up, left);

                if (first[row - 1] == second[col - 1]) {
                    int upLeft = lcs[row - 1][col - 1];
                    best = Math.max(1 + upLeft, best);
                }

                lcs[row][col] = best;
            }
        }

        return lcs;
    }

    private static Iterable<Character> getCharSequence(final char[] first, final char[] second, final int[][] lcs) {
        Deque<Character> result = new LinkedList<>();

        int currentRow = first.length;
        int currentCol = second.length;

        while (currentRow > 0 && currentCol > 0) {
            Character character = first[currentRow - 1];
            if (first[currentRow - 1] == second[currentCol - 1]) {
                currentCol--;
                currentRow--;
                result.addFirst(first[currentRow]);
            } else if (lcs[currentRow][currentCol] == lcs[currentRow - 1][currentCol]) {
                currentRow--;
            } else {
                currentCol--;
            }
        }

        return result;
    }
}
