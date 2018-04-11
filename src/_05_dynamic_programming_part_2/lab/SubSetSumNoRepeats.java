package _05_dynamic_programming_part_2.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class SubSetSumNoRepeats {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            Set<Integer> numbers = Arrays
                    .stream(reader.readLine().trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .filter(num -> num > 0) // This algorithm works only for numbers > 0
                    .collect(Collectors.toCollection(TreeSet::new)); // Optimization - sorted unique numbers

            int targetSum = Integer.parseInt(reader.readLine());

            boolean[] validSums = getValidSums(numbers, targetSum);

            printResult(targetSum, numbers, validSums);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static boolean[] getValidSums(final Iterable<Integer> numbers, final int targetSum) {
        boolean[] validSums = new boolean[targetSum + 1];
        validSums[0] = true;

        for (int sum = 0; sum < validSums.length; sum++) {
            if (validSums[sum]) {
                for (final int number : numbers) {
                    int newSum = sum + number;

                    if (newSum > targetSum) {
                        break; // Above targetSum
                    }

                    validSums[newSum] = true;
                }
            }
        }

        return validSums;
    }

    private static Iterable<Integer> getUsedNumbers(int targetSum, final Iterable<Integer> numbers, final boolean[] validSums) {
        Deque<Integer> usedNumbers = new LinkedList<>();

        while (targetSum > 0) {
            for (final Integer number : numbers) {
                int newSum = targetSum - number;
                if (newSum >= 0 && validSums[newSum]) {
                    usedNumbers.addFirst(number);
                    targetSum = newSum;
                }
            }
        }

        return usedNumbers;
    }

    private static void printResult(int targetSum, final Iterable<Integer> numbers, final boolean[] validSums) {
        if (!validSums[targetSum]) {
            System.out.printf("No numbers add up to %d%n", targetSum);
            return;
        }

        System.out.printf("%d = ", targetSum);

        Iterable<Integer> usedNumbers = getUsedNumbers(targetSum, numbers, validSums);

        System.out.println(usedNumbers.toString().replaceAll("[\\[\\]]", "").replaceAll(",", " +"));
    }
}
