package _12_exam_preparation.exam_2017_09_16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Pr01AbaspaBasapa {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final char[] first = reader.readLine().toCharArray();
        final char[] second = reader.readLine().toCharArray();

        final int[][] dp = new int[second.length + 1][first.length + 1];

        for (int row = 1; row <= second.length; row++) {
            final char ch1 = second[row - 1];
            for (int col = 1; col <= first.length; col++) {
                final char ch2 = first[col - 1];
                if (ch1 == ch2) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                }
            }
        }

        int startCol = 0;
        int bestLength = 0;

        for (int row = 1; row <= second.length; row++) {
            for (int col = 1; col <= first.length; col++) {
                if (dp[row][col] == 1) {
                    int nextRow = row + 1;
                    int nextCol = col + 1;
                    int currentLength = 1;
                    while (nextRow <= second.length &&
                            nextCol <= first.length &&
                            dp[nextRow][nextCol] == currentLength + 1) {
                        nextRow++;
                        nextCol++;
                        currentLength++;
                    }

                    if (currentLength > bestLength) {
                        bestLength = currentLength;
                        startCol = col;
                    }
                }
            }
        }

        if (bestLength > 0) {
            System.out.print(String.valueOf(first).substring(startCol - 1, startCol + bestLength - 1));
        }
    }
}
