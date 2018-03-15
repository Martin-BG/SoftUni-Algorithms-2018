package _01_recursion_sorting_searching.lab.recursion;

import java.util.Scanner;

public class Pr03RecursiveDrawing {

    public static void main(String[] args) {

        int n = Integer.parseInt(new Scanner(System.in).nextLine());

        draw(n);
    }

    private static void draw(int n) {

        if (n <= 0) {
            return;
        }

        System.out.println(new String(new char[n]).replace('\0', '*'));

        draw(n - 1);

        System.out.println(new String(new char[n]).replace('\0', '#'));
    }
}
