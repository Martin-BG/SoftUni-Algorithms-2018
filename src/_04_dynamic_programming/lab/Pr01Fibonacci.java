package _04_dynamic_programming.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Pr01Fibonacci {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            final int n = Integer.parseInt(reader.readLine());

            System.out.println(findFibonacciIterative(n));
//            System.out.println(findFibonacciRecursiveWithMemoization(new long[n+1], n));
        } catch (IOException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static long findFibonacciIterative(final int n) {
        if (n == 0) {
            return 0L;
        } else if (n <= 2) {
            return 1L;
        }

        long previous = 1L;
        long current = 1L;

        for (int i = 3; i <= n; i++) {
            long old = current;
            current += previous;
            previous = old;
        }

        return current;
    }

    private static long findFibonacciRecursiveWithMemoization(final long[] memo, final int n) {
        if (n <= 0) {
            return 0L;
        } else if (n <= 2) {
            memo[n] = 1L;
        }

        if (memo[n] > 0) {
            return memo[n];
        }

        long current = findFibonacciRecursiveWithMemoization(memo, n - 1) +
                findFibonacciRecursiveWithMemoization(memo, n - 2);

        memo[n] = current;

        return current;
    }
}
