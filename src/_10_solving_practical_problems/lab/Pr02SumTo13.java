package _10_solving_practical_problems.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Pr02SumTo13 {

    private static final int TARGET_SUM = 13;

    public static void main(final String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int[] numbers = Arrays
                .stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::valueOf)
                .toArray();

        System.out.println(sum(numbers, 0, 0) ? "Yes" : "No");
    }

    private static boolean sum(int[] numbers, int index, int currentSum) {
        if (index == numbers.length) {
            return currentSum == TARGET_SUM;
        }

        return sum(numbers, index + 1, currentSum + numbers[index]) ||
                sum(numbers, index + 1, currentSum - numbers[index]);
    }
}
