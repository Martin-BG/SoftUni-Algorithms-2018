package _04_dynamic_programming.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Pr01Fibonacci {

    private static final Charset ENCODING = Charset.forName("UTF-8");

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {

            final int n = Integer.parseInt(reader.readLine());

            System.out.println(findFibonacci(n));
        } catch (IOException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static long findFibonacci(final int n) {
        if (n <= 2) {
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

    private static long findFibonacciWithMemoization(final int n) {
        if (n <= 2) {
            return 1L;
        }

        final long[] fibonacci = new long[n + 1];

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci[n];
    }
}
