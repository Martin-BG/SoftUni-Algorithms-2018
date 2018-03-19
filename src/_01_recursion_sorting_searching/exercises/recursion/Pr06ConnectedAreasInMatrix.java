package _01_recursion_sorting_searching.exercises.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Pr06ConnectedAreasInMatrix {

    private static final char BLOCKED_SYMBOL = '*';
    private static final char FREE_SYMBOL = '-';

    private static final char[][] MATRIX = readMatrix();
    private static final Set<Area> AREAS = new TreeSet<>();


    public static void main(String[] args) {

        findConnectedAreas();

        printResult();
    }

    private static void findConnectedAreas() {
        for (int row = 0; row < MATRIX.length; row++) {
            for (int col = 0; col < MATRIX[row].length; col++) {
                if (MATRIX[row][col] != FREE_SYMBOL) {
                    continue;
                }

                Area area = new Area(row, col);

                exploreArea(area, row, col);

                AREAS.add(area);
            }
        }
    }

    private static void exploreArea(Area area, int row, int col) {
        if (isFreeCell(row, col)) {
            area.size++;
            MATRIX[row][col] = BLOCKED_SYMBOL;

            exploreArea(area, row, col + 1);
            exploreArea(area, row + 1, col);
            exploreArea(area, row, col - 1);
            exploreArea(area, row - 1, col);
        }
    }

    private static boolean isFreeCell(int row, int col) {
        return row >= 0 && row < MATRIX.length &&
                col >= 0 && col < MATRIX[row].length &&
                MATRIX[row][col] == FREE_SYMBOL;
    }

    private static char[][] readMatrix() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int rows = Integer.parseInt(reader.readLine());
            int cols = Integer.parseInt(reader.readLine());

            char[][] matrix = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                matrix[i] = reader.readLine().trim().toCharArray();
            }

            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new char[][]{};
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Total areas found: %d%n", AREAS.size()));

        final int[] areaIndex = {1};

        AREAS.forEach(area -> sb.append(
                String.format("Area #%d at %s%n", areaIndex[0]++, area)));

        System.out.println(sb.toString().trim());
    }

    private static final class Area implements Comparable<Area> {
        final int row;
        final int col;
        int size;

        Area(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 0;
        }

        @Override
        public int compareTo(Area other) {
            if (this.size == other.size) {
                int cmp = this.row - other.row;

                if (cmp == 0) {
                    cmp = this.col - other.col;
                }

                return cmp;
            }

            return other.size - this.size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Area)) {
                return false;
            }

            Area other = (Area) o;

            return this.compareTo(other) == 0;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            result = 31 * result + size;
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d), size: %d",
                    this.row, this.col, this.size);
        }
    }

}
