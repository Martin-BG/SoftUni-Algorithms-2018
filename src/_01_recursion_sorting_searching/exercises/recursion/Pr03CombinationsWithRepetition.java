package _01_recursion_sorting_searching.exercises.recursion;

import java.util.Scanner;

public class Pr03CombinationsWithRepetition {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        findCombinations(new int[k], 1, n, 0);
    }

    private static void findCombinations(int[] array, int start, int n, int index) {
        if (index >= array.length) {
            StringBuilder sb = new StringBuilder();
            for (int anArray : array) {
                sb.append(anArray).append(" ");
            }
            System.out.println(sb.toString());
            return;
        }

        for (int i = start; i <= n; i++) {
            array[index] = i;
            findCombinations(array, i, n, index + 1);
        }
    }
}
