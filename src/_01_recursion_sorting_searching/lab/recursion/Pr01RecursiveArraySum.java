package _01_recursion_sorting_searching.lab.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr01RecursiveArraySum {

    public static void main(String[] args) {

        int[] nums = readNumbers();

        System.out.println(sum(nums, 0));
    }

    private static long sum(int[] arr, int index) {

        if (index == arr.length - 1) {
            return arr[index];
        }

        return arr[index] + sum(arr, index + 1);
    }

    private static int[] readNumbers() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return Arrays
                    .stream(reader.readLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[]{};
    }
}
