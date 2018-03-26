package _02_combinatorial_algorithms.lab;

import java.util.Scanner;

public class Pr07NChooseKCountRecursive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        long result = binomialCoefficientRecursion(n, k);

        System.out.println(result);
    }

    private static long binomialCoefficientRecursion(int n, int k) {
        if (k > n) {
            return 0L;
        }

        if (k == 0 || k == n) {
            return 1L;
        }

        return binomialCoefficientRecursion(n - 1, k - 1) + binomialCoefficientRecursion(n - 1, k);
    }
}
