package _01_recursion_sorting_searching.lab.sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Pr01CountSort {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().trim().split("\\s+");

        Map<Integer, Integer> counts = new TreeMap<>();

        for (String numStr : str) {
            Integer num = Integer.valueOf(numStr);

            counts.putIfAbsent(num, 0);
            counts.replace(num, counts.get(num) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> kvp : counts.entrySet()) {
            String entry = String.format("%d ", kvp.getKey());
            for (int i = 0; i < kvp.getValue(); i++) {
                sb.append(entry);
            }
        }
        System.out.println(sb.toString());
    }
}
