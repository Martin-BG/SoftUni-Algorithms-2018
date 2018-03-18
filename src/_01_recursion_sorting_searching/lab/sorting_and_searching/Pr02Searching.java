package _01_recursion_sorting_searching.lab.sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr02Searching {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().split("\\s+");
        int number = Integer.valueOf(reader.readLine());

        int[] arr = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.valueOf(str[i]);
        }

        int index = findIndexOf(arr, number);

        System.out.println(index);
    }

    private static int findIndexOf(int[] arr, int number) {

        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (number < arr[mid]) {
                hi = mid - 1;
            } else if (number > arr[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
