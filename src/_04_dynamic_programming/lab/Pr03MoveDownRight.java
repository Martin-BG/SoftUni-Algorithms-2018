package _04_dynamic_programming.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Pr03MoveDownRight {

    public static void main(final String[] args) {

        int[][] matrix = readMatrix();

        findBestSums(matrix);

        List<String> path = getPath(matrix);

        System.out.println(path.toString()
                .replaceAll("\\[\\[", "[")
                .replaceAll("]]|],", "]"));
    }

    private static List<String> getPath(final int[][] matrix) {
        List<String> path = new LinkedList<>();
        int currentRow = matrix.length - 1;
        int currentCol = matrix[currentRow].length - 1;
        while (true) {

            path.add(String.format("[%d, %d]", currentRow, currentCol));

            if (currentRow == 0 && currentCol == 0) {
                break;
            }

            if (currentRow == 0) {
                currentCol--; // Left is the only option
            } else if (currentCol == 0) {
                currentRow--; // Up is the only option
            } else {
                int up = matrix[currentRow - 1][currentCol];
                int left = matrix[currentRow][currentCol - 1];
                if (up > left) {
                    currentRow--; // Up - higher value
                } else {
                    currentCol--; // Left - higher or equal value
                }
            }
        }

        Collections.reverse(path);
        return path;
    }

    private static void findBestSums(final int[][] matrix) {
        for (int col = 1; col < matrix[0].length; col++) {
            matrix[0][col] += matrix[0][col - 1];
        }

        for (int row = 1; row < matrix.length; row++) {
            matrix[row][0] += matrix[row - 1][0];
        }

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                int current = matrix[row][col];
                int up = matrix[row - 1][col];
                int left = matrix[row][col - 1];
                matrix[row][col] = Math.max(current + up, current + left);
            }
        }
    }

    private static int[][] readMatrix() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int rows = Integer.parseInt(reader.readLine());
            int cols = Integer.parseInt(reader.readLine());

            int[][] matrix = new int[rows][cols];

            for (int row = 0; row < rows; row++) {
                matrix[row] = Arrays
                        .stream(reader.readLine().trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            return matrix;
        } catch (IOException | NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }

        return new int[0][0];
    }
}
