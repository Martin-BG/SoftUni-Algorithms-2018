package _03_greedy_algorithms.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pr01FractionalKnapsack {

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

            double capacity = Double.parseDouble(reader.readLine().split(PARAMETERS_SEPARATOR)[1].trim());
            int itemsCount = Integer.parseInt(reader.readLine().split(PARAMETERS_SEPARATOR)[1].trim());

            final List<double[]> items = new ArrayList<>();
            while (itemsCount-- > 0) {
                items.add(Arrays.stream(reader.readLine().split(ITEMS_SEPARATOR))
                        .mapToDouble(Double::parseDouble)
                        .toArray());
            }

            items.sort((a, b) -> Double.compare(b[PRICE] / b[QUANTITY], a[PRICE] / a[QUANTITY]));

            double totalValue = 0d;
            double availableCapacity = capacity;

            final StringBuilder sb = new StringBuilder();

            for (double[] item : items) {
                double itemQuantity = item[QUANTITY];
                double itemPrice = item[PRICE];
                double percentageTaken = Math.min(itemQuantity, availableCapacity) / itemQuantity;
                double valueTaken = percentageTaken * item[PRICE];

                String percentage = (percentageTaken < 1d) ?
                        String.format(FRACTION_FORMAT, percentageTaken * 100d) :
                        Integer.toString(100);

                sb.append(String.format(TAKEN, percentage, itemPrice, itemQuantity));

                totalValue += valueTaken;
                availableCapacity -= itemQuantity * percentageTaken;

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
}
