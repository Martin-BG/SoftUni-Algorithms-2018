package _05_dynamic_programming_part_2.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pr01Knapsack {

    public static void main(final String[] args) {

        List<Item> items = new ArrayList<>();

        int maxCapacity = readInputData(items);

        List<Item> takenItems = fillKnapsack(items, maxCapacity);

        printResult(takenItems);
    }

    private static List<Item> fillKnapsack(final List<Item> items, final int maxCapacity) {
        int[][] values = new int[items.size() + 1][maxCapacity + 1];
        boolean[][] isItemTaken = new boolean[items.size() + 1][maxCapacity + 1];

        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
            Item item = items.get(itemIndex);

            for (int capacity = item.weight; capacity <= maxCapacity; capacity++) {
                int rowIndex = itemIndex + 1;

                int valIncluded = item.value + values[itemIndex][capacity - item.weight];
                int valExcluded = values[rowIndex - 1][capacity];

                if (valIncluded > valExcluded) {
                    values[rowIndex][capacity] = valIncluded;
                    isItemTaken[rowIndex][capacity] = true;
                } else {
                    values[rowIndex][capacity] = valExcluded;
                }
            }
        }

//        int maxValue = values[items.size()][maxCapacity];

        List<Item> taken = new ArrayList<>();

        int capacity = maxCapacity;

        for (int itemIndex = items.size() - 1; itemIndex >= 0 || capacity <= 0; itemIndex--) {
            if (isItemTaken[itemIndex + 1][capacity]) {
                Item item = items.get(itemIndex);
                taken.add(item);
                capacity -= item.weight;
            }
        }

        return taken;
    }

    private static void printResult(final List<Item> takenItems) {
        int totalWeight = takenItems.stream().mapToInt(item -> item.weight).sum();
        int totalValue = takenItems.stream().mapToInt(item -> item.value).sum();
        takenItems.sort(Comparator.comparing(item -> item.name));

        final StringBuilder sb = new StringBuilder()
                .append(String.format("Total Weight: %d%n", totalWeight))
                .append(String.format("Total Value: %d%n", totalValue));
        takenItems.forEach(item -> sb.append(item.name).append(System.lineSeparator()));

        System.out.println(sb.toString().trim());
    }

    private static int readInputData(final List<Item> items) {
        int maxCapacity = 0;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            maxCapacity = Integer.parseInt(reader.readLine());

            while (true) {
                String input = reader.readLine().trim();

                if ("end".equalsIgnoreCase(input)) {
                    break;
                }

                String[] tokens = input.split("\\s+");
                String name = tokens[0];
                int weight = Integer.parseInt(tokens[1]);
                int value = Integer.parseInt(tokens[2]);

                items.add(new Item(name, weight, value));
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return maxCapacity;
    }

    private static final class Item {
        String name;
        int weight;
        int value;

        Item(final String name, final int weight, final int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }
}
