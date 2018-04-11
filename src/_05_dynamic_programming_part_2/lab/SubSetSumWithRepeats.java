package _05_dynamic_programming_part_2.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SubSetSumWithRepeats {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int[] numbers = Arrays
                    .stream(reader.readLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int targetSum = Integer.parseInt(reader.readLine());

            Map<Integer, Integer> sums = calcSums(numbers);

            printResult(targetSum, sums);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static Deque<Integer> getUsedNumbers(int targetSum, final Map<Integer, Integer> sums) {
        Deque<Integer> usedNumbers = new LinkedList<>();

        while (targetSum > 0) {
            int number = sums.get(targetSum);
            usedNumbers.addFirst(number);
            targetSum -= number;
        }

        return usedNumbers;
    }

    private static Map<Integer, Integer> calcSums(final int[] numbers) {
        Map<Integer, Integer> sums = new HashMap<>();

        sums.put(0, 0);

        for (final int number : numbers) {
            Set<Integer> integers = new HashSet<>(sums.keySet());
            for (final Integer sum : integers) {
                sums.putIfAbsent(number + sum, number);
            }
        }

        return sums;
    }


    private static void printResult(int targetSum, final Map<Integer, Integer> sums) {
        if (sums.containsKey(targetSum)) {
            System.out.printf("%d = ", targetSum);

            Deque<Integer> usedNumbers = getUsedNumbers(targetSum, sums);

            System.out.println(usedNumbers.toString().replaceAll("[\\[\\]]", "").replaceAll(",", " +"));

        } else {
            System.out.printf("No numbers add up to %d%n", targetSum);
        }
    }
}
