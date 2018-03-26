package _02_combinatorial_algorithms.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr05CombinationsWithoutRepetitionRecursive {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().trim().split("\\s+");
        int k = Integer.parseInt(reader.readLine());

        String[] variation = new String[k];

        generateCombinations(variation, elements, 0, 0);
    }

    private static void generateCombinations(String[] variation, String[] elements, int index, int start) {
        if (index >= variation.length) {
            System.out.println(String.join(" ", variation));
        } else {
            for (int i = start; i < elements.length; i++) {
                variation[index] = elements[i];
                generateCombinations(variation, elements, index + 1, i + 1);
            }
        }
    }
}
