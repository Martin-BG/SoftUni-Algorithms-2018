package _02_combinatorial_algorithms.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr04VariationsWithRepetitionsRecursive {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().trim().split("\\s+");
        int k = Integer.parseInt(reader.readLine());

        String[] variation = new String[k];

        generateVariations(elements, variation, k, 0);
    }

    private static void generateVariations(String[] elements, String[] variation, int k, int index) {
        if (index >= k) {
            System.out.println(String.join(" ", variation));
        } else {
            for (String element : elements) {
                variation[index] = element;
                generateVariations(elements, variation, k, index + 1);
            }
        }
    }
}
