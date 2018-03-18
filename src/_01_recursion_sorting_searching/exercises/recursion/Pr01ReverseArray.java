package _01_recursion_sorting_searching.exercises.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr01ReverseArray {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        reverseArray(array, 0, array.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int number : array) {
            sb.append(number).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void reverseArray(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;

        reverseArray(array, ++left, --right);
    }
}
