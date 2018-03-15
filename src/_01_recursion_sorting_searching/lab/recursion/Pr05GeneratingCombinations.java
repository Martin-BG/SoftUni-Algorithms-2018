package _01_recursion_sorting_searching.lab.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr05GeneratingCombinations {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int k = Integer.parseInt(reader.readLine());


        genComb(nums, new int[k], 0, 0);
    }

    private static void genComb(int[] elements, int[] combination, int elementIndex, int combinationIndex) {

        if (combinationIndex >= combination.length) {
            System.out.println(Arrays.toString(combination).replaceAll("[,\\[\\]]", ""));
            return;
        }

        for (int i = elementIndex; i < elements.length; i++) {
            combination[combinationIndex] = elements[i];
            genComb(elements, combination, i + 1, combinationIndex + 1);
        }
    }
}
