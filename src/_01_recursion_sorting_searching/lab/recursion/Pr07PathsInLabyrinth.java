package _01_recursion_sorting_searching.lab.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr07PathsInLabyrinth {

    private static final char[][] LABYRINTH = readLabyrinth();
    private static final Cell EXIT_CELL = findExitCell();
    private static final char BLOCKED_SYMBOL = '*';
    private static final Set<Cell> BLOCKED_CELLS = readBlockedCells();
    private static final char EXIT_SYMBOL = 'e';

    private static List<String> path = new LinkedList<>();
    private static Set<Cell> visied = new HashSet<>();

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
        if (!inBounds(current)) {
            return;
        }

        path.add(direction);

        if (current == EXIT_CELL) {
            System.out.println(String.join("", path));
        } else if (!visied.contains(current) && !BLOCKED_CELLS.contains(current)) {

            visied.add(current);
            findPath(new Cell(current.row, current.col + 1), "R");
            findPath(new Cell(current.row + 1, current.col), "D");
            findPath(new Cell(current.row, current.col - 1), "L");
            findPath(new Cell(current.row - 1, current.col), "U");
            visied.remove(current);
        }

        path.remove(path.size() - 1);
    }

    private static boolean inBounds(Cell cell) {
        return cell.row < LABYRINTH.length && cell.col < LABYRINTH[cell.row].length;
    }

    private static Cell findExitCell() {
        for (int row = 0; row < LABYRINTH.length; row++) {
            for (int col = 0; col < LABYRINTH[row].length; col++) {
                if (LABYRINTH[row][col] == 'e') {
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
        int row;
        int col;

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
