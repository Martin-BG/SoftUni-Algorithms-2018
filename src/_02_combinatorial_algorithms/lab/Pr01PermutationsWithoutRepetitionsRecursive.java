package _02_combinatorial_algorithms.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr01PermutationsWithoutRepetitionsRecursive {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().trim().split("\\s+");

        generatePermutations(0, elements);
    }

    private static void generatePermutations(int index, String[] elements) {
        if (index >= elements.length) {
            System.out.println(String.join(" ", elements));
        } else {
            generatePermutations(index + 1, elements);

            for (int i = index + 1; i < elements.length; i++) {
                swap(index, i, elements);
                generatePermutations(index + 1, elements);
                swap(i, index, elements);
            }
        }
    }

    private static void swap(int i, int j, String[] elements) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
