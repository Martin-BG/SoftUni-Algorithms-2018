package _12_exam_preparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class Pr01ShootingRange {

    public static void main(final String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final String[] tokens = reader.readLine().split("\\s+");
        final int targetSum = Integer.parseInt(reader.readLine());

        final int[][] targets = new int[tokens.length][tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            int target = Integer.valueOf(tokens[i]);
            for (int j = 0; j < tokens.length; j++) {
                targets[i][j] = target * (j + 1);
            }
        }

        final StringBuilder sb = new StringBuilder();

        findSums(targetSum,
                targets,
                0,
                0,
                new Integer[tokens.length],
                new boolean[tokens.length],
                sb);

        System.out.print(sb.toString());
    }

    private static void findSums(final int targetSum,
                                 final int[][] targets,
                                 final int currentSum,
                                 final int currentTarget,
                                 final Integer[] usedTargets,
                                 final boolean[] used,
                                 final StringBuilder result) {

        if (currentSum == targetSum) {
            for (int i = 0; i < usedTargets.length && usedTargets[i] != null; i++) {
                result.append(usedTargets[i]).append(" ");
            }
            result.deleteCharAt(result.length() - 1).append(System.lineSeparator());
        }

        if (currentSum >= targetSum) {
            return;
        }

        final Set<Integer> usedLocal = new HashSet<>();

        for (int i = 0; i < targets.length; i++) {
            final int currentNum = targets[i][0];

            if (used[i] || usedLocal.contains(currentNum)) {
                continue;
            }

            usedLocal.add(currentNum);

            usedTargets[currentTarget] = currentNum;
            used[i] = true;

            findSums(targetSum,
                    targets,
                    currentSum + targets[i][currentTarget],
                    currentTarget + 1,
                    usedTargets,
                    used,
                    result);

            usedTargets[currentTarget] = null;
            used[i] = false;
        }
    }
}
