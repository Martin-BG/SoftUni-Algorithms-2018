package _01_recursion_sorting_searching.exercises.recursion;

import java.util.Scanner;

public class Pr02NestedLoops {

    public static void main(String[] args) {

        int levels = Integer.parseInt(new Scanner(System.in).nextLine());

        nestedLoops(new int[levels], 0);
    }

    private static void nestedLoops(int[] array, int start) {
        if (start == array.length) {
            StringBuilder sb = new StringBuilder();
            for (int anArray : array) {
                sb.append(anArray).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }

        for (int i = 1; i <= array.length; i++) {
            array[start] = i;
            nestedLoops(array, start + 1);
        }
    }
}
