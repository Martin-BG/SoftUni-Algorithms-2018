package _13_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Pr04Xelnaga {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String[] tokens = reader.readLine().split("\\s");

        Map<Integer, Integer> agesByCount = new HashMap<>();
        for (int i = 0; i < tokens.length - 1; i++) {
            int current = Integer.parseInt(tokens[i]) + 1;
            agesByCount.putIfAbsent(current, 0);
            agesByCount.replace(current, agesByCount.get(current) + 1);
        }

        final int[] races = {0};
        agesByCount.forEach((k, v) -> races[0] += k * Math.ceil((double) v / k));
        System.out.println(races[0]);
    }
}
