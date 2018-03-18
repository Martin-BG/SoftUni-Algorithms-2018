package _01_recursion_sorting_searching.lab.sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr01MergeSort {

    private static int[] aux;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().split("\\s+");

        int length = str.length;
        aux = new int[length];
        arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = Integer.valueOf(str[i]);
        }

        mergeSort(0, length - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void mergeSort(int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        mergeSort(lo, mid);
        mergeSort(mid + 1, hi);

        // Merge

        if (arr[mid] < arr[mid + 1]) {
            return;
        }

        System.arraycopy(arr, lo, aux, lo, hi + 1 - lo);

        int i = lo;
        int j = mid + 1;
        for (int index = lo; index <= hi; index++) {
            if (i > mid) {
                arr[index] = aux[j++];
            } else if (j > hi) {
                arr[index] = aux[i++];
            } else if (aux[i] < aux[j]) {
                arr[index] = aux[i++];
            } else {
                arr[index] = aux[j++];
            }
        }
    }
}
