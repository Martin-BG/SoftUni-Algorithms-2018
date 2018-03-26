package _02_combinatorial_algorithms.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr01IterativePermutationsWithoutRepetitions {

    // TODO - refactor
    // http://www.quickperm.org/01example.php
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().trim().split("\\s+");

        int p[] = new int[elements.length + 1];

        for (int i = 0; i <= elements.length; i++)   // initialize arrays; a[N] can be any type
        {
            p[i] = i;
        }
        // p[N] > 0 controls iteration and the index boundary for i

        System.out.println(String.join(" ", elements));

        int i = 1;   // setup first swap points to be 1 and 0 respectively (i & j)
        while (i < elements.length) {
            p[i]--;                 // decrease index "weight" for i by one
            int j = i % 2 * p[i];   // IF i is odd then j = p[i] otherwise j = 0

            swap(i, j, elements);

            System.out.println(String.join(" ", elements));

            i = 1;               // reset index i to 1 (assumed)
            while (p[i] == 0)    // while (p[i] == 0)
            {
                p[i] = i;        // reset p[i] zero value
                i++;             // set new index value for i (increase by one)
            } // while(!p[i])
        } // while(i < N)

    }

    private static void swap(int i, int j, String[] elements) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
