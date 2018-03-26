package _02_combinatorial_algorithms.lab;

import java.util.Scanner;

public class Pr07NChooseKCountDynamicProgramming {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        long result = binomialCoefficientDynamicProgramming(n, k);

        System.out.println(result);
    }

    // https://www.geeksforgeeks.org/dynamic-programming-set-9-binomial-coefficient/
    private static long binomialCoefficientDynamicProgramming(int n, int k) {
        long[] dp = new long[k + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // Compute next row of Pascal triangle using the previous row
            for (int j = Math.min(i, k); j > 0; j--) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }

        return dp[k];
    }
}
