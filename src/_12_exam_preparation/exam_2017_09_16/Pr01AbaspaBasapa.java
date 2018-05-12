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

        int maxLength = 0;
        int startIndex = 0;

        final int[][] dp = new int[second.length + 1][first.length + 1];

        for (int col = 1; col <= first.length; col++) {
            final char ch1 = first[col - 1];
            for (int row = 1; row <= second.length; row++) {
                final char ch2 = second[row - 1];
                if (ch1 == ch2) {
                    int length = dp[row - 1][col - 1] + 1;
                    dp[row][col] = length;

                    if (length > maxLength) {
                        maxLength = length;
                        startIndex = col - length;
                    }
                }
            }
        }

        if (maxLength > 0) {
            System.out.print(String.valueOf(first).substring(startIndex, startIndex + maxLength));
        }
    }
}
