package _01_recursion_sorting_searching.lab.recursion;

import java.util.Scanner;

public class Pr02RecursiveFactorial {

    public static void main(String[] args) {

        int n = Integer.parseInt(new Scanner(System.in).nextLine());

        System.out.println(factorial(n));

    }

    private static long factorial(int n) {

        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
