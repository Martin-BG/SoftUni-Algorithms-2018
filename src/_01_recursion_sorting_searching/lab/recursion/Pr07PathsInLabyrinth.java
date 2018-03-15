package _01_recursion_sorting_searching.lab.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr07PathsInLabyrinth {

    private static final char EXIT_SYMBOL = 'e';
    private static final char BLOCKED_SYMBOL = '*';

    private static final char[][] LABYRINTH = readLabyrinth();
    private static final Set<Cell> BLOCKED_CELLS = readBlockedCells();
    private static final Cell EXIT_CELL = findExitCell();

    private static final List<String> PATH = new LinkedList<>();
    private static final Set<Cell> VISITED = new HashSet<>();

    private static Set<Cell> readBlockedCells() {
        Set<Cell> cells = new HashSet<>();

        for (int row = 0; row < LABYRINTH.length; row++) {
            for (int col = 0; col < LABYRINTH[row].length; col++) {
                if (LABYRINTH[row][col] == BLOCKED_SYMBOL) {
                    cells.add(new Cell(row, col));
                }
            }
        }

        return cells;
    }

    public static void main(String[] args) {
        findPath(new Cell(0, 0), "");
    }

    private static void findPath(Cell current, String direction) {
        if (current == null) {
            return;
        }

        PATH.add(direction);

        if (current.equals(EXIT_CELL)) {
            System.out.println(String.join("", PATH));
        } else if (!VISITED.contains(current) && !BLOCKED_CELLS.contains(current)) {

            VISITED.add(current);
            findPath(getCell(current.row, current.col + 1), "R");
            findPath(getCell(current.row + 1, current.col), "D");
            findPath(getCell(current.row, current.col - 1), "L");
            findPath(getCell(current.row - 1, current.col), "U");
            VISITED.remove(current);
        }

        PATH.remove(PATH.size() - 1);
    }

    private static Cell getCell(int row, int col) {
        if (row < 0 || col < 0 || row >= LABYRINTH.length || col >= LABYRINTH[row].length) {
            return null;
        }

        return new Cell(row, col);
    }

    private static Cell findExitCell() {
        for (int row = 0; row < LABYRINTH.length; row++) {
            for (int col = 0; col < LABYRINTH[row].length; col++) {
                if (LABYRINTH[row][col] == EXIT_SYMBOL) {
                    return new Cell(row, col);
                }
            }
        }

        return null;
    }

    private static char[][] readLabyrinth() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int rows = Integer.parseInt(reader.readLine());
            int cols = Integer.parseInt(reader.readLine());

            char[][] labyrinth = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                labyrinth[i] = reader.readLine().trim().toCharArray();
            }

            return labyrinth;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new char[][]{};
    }

    private static class Cell implements Comparable<Cell> {
        final int row;
        final int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Cell cell) {
            if (this.row == cell.row && this.col == cell.col) {
                return 0;
            }

            return 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Cell)) {
                return false;
            }
            Cell cell = (Cell) o;
            return row == cell.row &&
                    col == cell.col;
        }

        @Override
        public int hashCode() {

            return Objects.hash(row, col);
        }
    }
}
