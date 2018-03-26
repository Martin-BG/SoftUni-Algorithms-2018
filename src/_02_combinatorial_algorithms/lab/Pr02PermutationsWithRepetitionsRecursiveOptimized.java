package _02_combinatorial_algorithms.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr02PermutationsWithRepetitionsRecursiveOptimized {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().trim().split("\\s+");

        Arrays.sort(elements);

        generatePermutations(elements, 0, elements.length - 1);
    }

    private static void generatePermutations(String[] elements, int start, int end) {
        System.out.println(String.join(" ", elements));

        for (int left = end - 1; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                if (!elements[left].equals(elements[right])) {
                    swap(left, right, elements);
                    generatePermutations(elements, left + 1, end);
                }
            }
            String firstElement = elements[left];
/*            for (int i = left; i <= end -1; i++) {
                elements[i] = elements[i + 1];
            }*/
            System.arraycopy(elements, left + 1, elements, left, end - left);
            elements[end] = firstElement;
        }
    }

    private static void swap(int i, int j, String[] elements) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
