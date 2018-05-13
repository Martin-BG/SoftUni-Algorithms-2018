package _13_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Pr03Terran {

    private static int counter = 0;

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        char[] elements = reader.readLine().trim().toCharArray();

        Arrays.sort(elements);

        generatePermutations(elements, 0, elements.length - 1);

        System.out.println(counter);
    }

    private static void generatePermutations(char[] elements, int start, int end) {
//        System.out.println(String.valueOf(elements));
        counter++;

        for (int left = end - 1; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                if (elements[left] != elements[right]) {
                    swap(left, right, elements);
                    generatePermutations(elements, left + 1, end);
                }
            }
            char firstElement = elements[left];
            System.arraycopy(elements, left + 1, elements, left, end - left);
            elements[end] = firstElement;
        }
    }

    private static void swap(int i, int j, char[] elements) {
        char temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
