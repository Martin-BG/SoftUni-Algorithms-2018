package _03_greedy_algorithms.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Pr01FractionalKnapsackQueueAndClass {

    private static final int PRICE = 0;
    private static final int QUANTITY = 1;
    private static final Charset ENCODING = Charset.forName("UTF-8");
    private static final String PARAMETERS_SEPARATOR = ":";
    private static final String ITEMS_SEPARATOR = " -> ";
    private static final String TAKEN = "Take %s%% of item with price %.2f and weight %.2f%n";
    private static final String TOTAL_PRICE = "Total price: %.2f";
    private static final String FRACTION_FORMAT = "%.2f";

    public static void main(String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {

            final double capacity = Double.parseDouble(reader.readLine().split(PARAMETERS_SEPARATOR)[1].trim());
            int itemsCount = Integer.parseInt(reader.readLine().split(PARAMETERS_SEPARATOR)[1].trim());

            final Queue<Item> items = new PriorityQueue<>(
                    Comparator.comparingDouble(item -> -item.price / item.quantity));

            while (itemsCount-- > 0) {
                double[] params = Arrays.stream(reader.readLine().split(ITEMS_SEPARATOR))
                        .mapToDouble(Double::parseDouble)
                        .toArray();
                items.add(new Item(params[PRICE], params[QUANTITY]));
            }

            double totalValue = 0d;
            double availableCapacity = capacity;

            final StringBuilder sb = new StringBuilder();

            while (!items.isEmpty()) {
                Item item = items.remove();

                double percentageTaken = Math.min(item.quantity, availableCapacity) / item.quantity;
                double valueTaken = percentageTaken * item.price;

                String percentage = (percentageTaken < 1d) ?
                        String.format(FRACTION_FORMAT, percentageTaken * 100d) :
                        Integer.toString(100);

                sb.append(String.format(TAKEN, percentage, item.price, item.quantity));

                totalValue += valueTaken;
                availableCapacity -= item.quantity * percentageTaken;

                if (availableCapacity <= 0d) {
                    break;
                }
            }

            sb.append(String.format(TOTAL_PRICE, totalValue));

            System.out.println(sb);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static class Item {
        private final double price;
        private final double quantity;

        Item(final double price, final double quantity) {
            this.price = price;
            this.quantity = quantity;
        }
    }
}
