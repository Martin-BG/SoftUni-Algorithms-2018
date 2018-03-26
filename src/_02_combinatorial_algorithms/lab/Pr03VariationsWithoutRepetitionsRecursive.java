package _02_combinatorial_algorithms.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr03VariationsWithoutRepetitionsRecursive {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().trim().split("\\s+");
        int k = Integer.parseInt(reader.readLine());

        String[] variation = new String[k];

        boolean[] used = new boolean[elements.length];

        generateVariations(elements, variation, used, k, 0);
    }

    private static void generateVariations(String[] elements, String[] variation, boolean[] used, int k, int index) {
        if (index >= k) {
            System.out.println(String.join(" ", variation));
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (used[i]) {
                    continue;
                }

                used[i] = true;
                variation[index] = elements[i];
                generateVariations(elements, variation, used, k, index + 1);
                used[i] = false;
            }
        }
    }
}
