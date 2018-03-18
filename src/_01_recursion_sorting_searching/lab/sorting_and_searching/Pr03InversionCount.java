package _01_recursion_sorting_searching.lab.sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr03InversionCount {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().split("\\s+");

        int[] arr = new int[str.length];
        int[] aux = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.valueOf(str[i]);
        }

        int inversions = findInversions(arr, aux, 0, str.length - 1);

        System.out.println(inversions);
    }

    private static int findInversions(int[] arr, int aux[], int lo, int hi) {
        if (lo >= hi) {
            return 0;
        }

        int mid = (lo + hi) / 2;
        int inv_count = findInversions(arr, aux, lo, mid);
        inv_count += findInversions(arr, aux, mid + 1, hi);

        // Merge - https://www.geeksforgeeks.org/counting-inversions/
        int left = lo;
        int right = ++mid;
        int index = lo;
        while ((left < mid) && (right <= hi)) {
            if (arr[left] <= arr[right]) {
                aux[index++] = arr[left++];
            } else {
                aux[index++] = arr[right++];
                inv_count += mid - left;
            }
        }

        while (left < mid) {
            aux[index++] = arr[left++];
        }

        while (right <= hi) {
            aux[index++] = arr[right++];
        }

        for (left = lo; left <= hi; left++) {
            arr[left] = aux[left];
        }

        return inv_count;
    }
}
