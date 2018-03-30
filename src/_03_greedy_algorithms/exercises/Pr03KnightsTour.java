package _03_greedy_algorithms.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Pr03KnightsTour {

    private static final Charset ENCODING = Charset.forName("UTF-8");
    private static final String STEP_FORMAT = "%4d";
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int START_ROW = 0;
    private static final int START_COLUMN = 0;
    private static final int[][] MOVES = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {
            final int boardSide = Integer.parseInt(reader.readLine());
            final int[][] board = new int[boardSide][boardSide];

            genMoves(board, boardSide);
            printBoard(board, boardSide);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static void printBoard(final int[][] board, final int boardSide) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < boardSide; i++) {
            for (int j = 0; j < boardSide; j++) {
                sb.append(String.format(STEP_FORMAT, board[i][j]));
            }
            sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }

    private static void genMoves(final int[][] board, final int boardSide) {
        int row = START_ROW;
        int col = START_COLUMN;
        int step = 1;

        while (true) {
            board[row][col] = step++;

            int[] bestMove = null;

            int bestMoveOptions = Integer.MAX_VALUE;

            for (final int[] move : MOVES) {
                int availableMoves = evaluateMoves(board, row + move[ROW], col + move[COL], boardSide);

                if (availableMoves >= bestMoveOptions || availableMoves == -1) {
                    continue;
                }

                bestMoveOptions = availableMoves;
                bestMove = move;
            }

            if (bestMove != null) {
                row = row + bestMove[ROW];
                col = col + bestMove[COL];
                continue;
            }

            break;
        }
    }

    private static int evaluateMoves(final int[][] board, final int row, final int col, final int boardSide) {
        if (!isValid(board, row, col, boardSide)) {
            return -1;
        }

        int validMoves = 0;
        for (final int[] move : MOVES) {
            int nextRow = row + move[ROW];
            int nextCol = col + move[COL];
            if (isValid(board, nextRow, nextCol, boardSide)) {
                validMoves++;
            }
        }
        return validMoves;
    }

    private static boolean isValid(final int[][] board, final int row, final int col, final int boardSide) {
        return row >= 0 &&
                row < boardSide &&
                col >= 0 &&
                col < boardSide &&
                board[row][col] == 0;
    }
}
