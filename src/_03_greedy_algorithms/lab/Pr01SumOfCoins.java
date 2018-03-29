package _03_greedy_algorithms.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Pr01SumOfCoins {

    private static final String USED_COINS = "Number of coins to take: %d%n";
    private static final String COINS_PER_VALUE = "%d coin(s) with value %d%n";
    private static final String SEPARATOR = ", ";
    private static final String ERROR = "Error";
    private static final Charset ENCODING = Charset.forName("UTF-8");

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {

            int[] coins = Arrays
                    .stream(reader.readLine().substring(7).split(SEPARATOR))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int targetSum = Integer.parseInt(reader.readLine().substring(5));

            Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

            System.out.printf(USED_COINS,
                    usedCoins.values().stream().reduce(0, Integer::sum));

            usedCoins.forEach((k, v) ->
                    System.out.printf(COINS_PER_VALUE, v, k));

        } catch (IllegalArgumentException e) {
            System.out.println(ERROR);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {

        int[] descendingOrder = Arrays
                .stream(coins)
                .distinct()
                .map(i -> ~i).sorted().map(i -> ~i)
                .toArray();

        Map<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());

        for (int coin : descendingOrder) {
            int used = targetSum / coin;

            if (used != 0) {
                result.put(coin, used);
                targetSum %= coin;
            }
        }

        if (targetSum != 0) {
            throw new IllegalArgumentException();
        }

        return result;
    }
}
