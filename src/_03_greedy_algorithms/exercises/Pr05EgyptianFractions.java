package _03_greedy_algorithms.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Pr05EgyptianFractions {

    private static final Charset ENCODING = Charset.forName("UTF-8");
    private static final String ERROR = "Error (fraction is equal to or greater than 1)";
    private static final String FRACTION_SEPARATOR = "/";
    private static final String OUTPUT_START = "%d%s%d = ";
    private static final String OUTPUT_MID = "1%s%d + ";
    private static final String OUTPUT_END = "1%s%d";

    public static void main(String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {

            int[] numbers = Arrays
                    .stream(reader.readLine().trim().split(FRACTION_SEPARATOR))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int nom = numbers[0];
            int denominator = numbers[1];


            if (nom >= denominator) {
                System.out.print(ERROR);
                return;
            }

            System.out.printf(OUTPUT_START, nom, FRACTION_SEPARATOR, denominator);

            while (true) {
                if (denominator % nom == 0) {
                    System.out.printf(OUTPUT_END, FRACTION_SEPARATOR, (denominator / nom));
                    break;
                }

                int divider = (nom + denominator) / nom;

                System.out.printf(OUTPUT_MID, FRACTION_SEPARATOR, divider);

                nom = (nom * divider) - denominator;

                denominator = denominator * divider;
            }
        } catch (IOException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
