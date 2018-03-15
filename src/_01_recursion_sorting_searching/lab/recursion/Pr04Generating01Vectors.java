package _01_recursion_sorting_searching.lab.recursion;

import java.util.Arrays;
import java.util.Scanner;

public class Pr04Generating01Vectors {

    public static void main(String[] args) {

        int n = Integer.parseInt(new Scanner(System.in).nextLine());

        gen01(new int[n], 0);
    }

    private static void gen01(int[] vector, int index) {

        if (index >= vector.length) {
            System.out.println(Arrays.toString(vector).replaceAll("[, \\[\\]]", ""));
            return;
        }

        vector[index] = 0;
        gen01(vector, index + 1);

        vector[index] = 1;
        gen01(vector, index + 1);
    }
}
