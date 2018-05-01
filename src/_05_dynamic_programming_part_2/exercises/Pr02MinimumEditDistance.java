package _05_dynamic_programming_part_2.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

public class Pr02MinimumEditDistance {

    private static final String DELIMITER = "=";

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int costReplace = Integer.parseInt(reader.readLine().split(DELIMITER)[1].trim());
            int costInsert = Integer.parseInt(reader.readLine().split(DELIMITER)[1].trim());
            int costDelete = Integer.parseInt(reader.readLine().split(DELIMITER)[1].trim());
            String start = reader.readLine().split(DELIMITER)[1].trim();
            String goal = reader.readLine().split(DELIMITER)[1].trim();

            int[][] dp = getCostMatrix(costReplace, costInsert, costDelete, start, goal);

//            printCostMatrix(start, goal, dp);

            System.out.printf("Minimum edit distance: %d%n", dp[start.length()][goal.length()]);

            Deque<String> path = getPath(costReplace, costInsert, costDelete, start, goal, dp);

            path.forEach(System.out::println);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static int[][] getCostMatrix(final int costReplace,
                                         final int costInsert,
                                         final int costDelete,
                                         final String start,
                                         final String goal) {
        int[][] dp = new int[start.length() + 1][goal.length() + 1];

        for (int col = 1; col <= goal.length(); col++) {
            dp[0][col] = dp[0][col - 1] + costInsert;
        }

        for (int row = 1; row <= start.length(); row++) {
            dp[row][0] = dp[row - 1][0] + costDelete;
        }

        for (int row = 1; row <= start.length(); row++) {
            for (int col = 1; col <= goal.length(); col++) {

                if (start.charAt(row - 1) == goal.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                    continue;
                }

                int insert = dp[row][col - 1] + costInsert;
                int delete = dp[row - 1][col] + costDelete;
                int replace = dp[row - 1][col - 1] + costReplace;

                dp[row][col] = Math.min(insert, Math.min(delete, replace));
            }
        }
        return dp;
    }

    private static Deque<String> getPath(final int costReplace,
                                         final int costInsert,
                                         final int costDelete,
                                         final String start,
                                         final String goal,
                                         final int[][] dp) {
        Deque<String> path = new LinkedList<>();

        int row = start.length();
        int col = goal.length();

        while (dp[row][col] != 0) {
            int minCol = Math.max(0, col - 1);
            int minRow = Math.max(0, row - 1);

            int currentCost = dp[row][col];
            int insert = dp[row][minCol] + costInsert;
            int delete = dp[minRow][col] + costDelete;
            int replace = dp[minRow][minCol] + costReplace;

            if (start.charAt(minRow) == goal.charAt(minCol)) {
                row = minRow;
                col = minCol;
            } else if (insert == currentCost) {
                path.addFirst(String.format("INSERT(%d, %c)", minCol, goal.charAt(minCol)));
                col = minCol;
            } else if (replace == currentCost) {
                path.addFirst(String.format("REPLACE(%d, %c)", minRow, goal.charAt(minCol)));
                row = minRow;
                col = minCol;
            } else if (delete == currentCost) {
                path.addFirst(String.format("DELETE(%d)", minRow));
                row = minRow;
            }
        }

        return path;
    }

    private static void printCostMatrix(final String start,
                                        final String goal,
                                        final int[][] dp) {
        for (int row = 0; row < start.length() + 1; row++) {
            if (row == 0) {
                System.out.printf("%12c", ' ');
                for (int index = 0; index < goal.length(); index++) {
                    System.out.printf("%4d", index);
                }
                System.out.println(" -> Insert");
                System.out.printf("%12c", ' ');
                for (int index = 0; index < goal.length(); index++) {
                    System.out.printf("%4c", goal.charAt(index));
                }
                System.out.println(" (goal)");
                System.out.printf("%8c", ' ');
            } else {
                System.out.printf("%4d", row - 1);
                System.out.printf("%4c", start.charAt(row - 1));
            }
            for (int col = 0; col < goal.length() + 1; col++) {
                System.out.printf("%4d", dp[row][col]);
            }
            System.out.println();
        }
        System.out.println("Delete");
    }
}
