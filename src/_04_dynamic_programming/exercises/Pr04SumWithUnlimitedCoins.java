package _04_dynamic_programming.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// https://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/

public class Pr04SumWithUnlimitedCoins {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int[] coins = Arrays
                    .stream(reader.readLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int targetSum = Integer.parseInt(reader.readLine());

            int[] combinationsCount = new int[targetSum + 1];
            combinationsCount[0] = 1;   // targetSum = 0

            for (final int coin : coins) {
                for (int sum = 1; sum <= targetSum; sum++) {
                    if (coin <= sum) {
                        combinationsCount[sum] += combinationsCount[sum - coin];
                    }
                }
            }

            System.out.println(combinationsCount[targetSum]);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
