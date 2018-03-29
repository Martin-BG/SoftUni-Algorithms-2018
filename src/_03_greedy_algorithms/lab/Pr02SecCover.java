package _03_greedy_algorithms.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Pr02SecCover {

    private static final Charset ENCODING = Charset.forName("UTF-8");
    private static final String SEPARATOR = ", ";
    private static final String SETS_TAKEN = "Sets to take (%d):%n";

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {

            int[] universe = Arrays
                    .stream(reader.readLine().substring(10).split(SEPARATOR))
                    .mapToInt(Integer::parseInt)
                    .toArray();


            int numberOfSets = Integer.parseInt(reader.readLine().substring(16).trim());

            List<int[]> sets = new ArrayList<>();

            while (numberOfSets-- > 0) {
                sets.add(Arrays
                        .stream(reader.readLine().split(SEPARATOR))
                        .mapToInt(Integer::parseInt)
                        .toArray());
            }

            List<int[]> chosenSets = chooseSets(sets, universe);

            System.out.printf(SETS_TAKEN, chosenSets.size());

            chosenSets.forEach(s -> System.out
                    .println(Arrays.toString(s)
                            .replaceAll("\\[", "{ ")
                            .replaceAll("]", " }")));

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    static List<int[]> chooseSets(List<int[]> sets, int[] universe) {

        // TODO
        return sets;
/*        List<int[]> chosenSets = new ArrayList<>();

        return chosenSets;*/
    }
}
