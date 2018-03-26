package _02_combinatorial_algorithms.exercises;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pr04Snakes {

    private static final Set<String> VISITED = new HashSet<>();
    private static final Set<String> SNAKES = new HashSet<>();
    private static final StringBuilder RESULT = new StringBuilder();

    private static int foundSnakes = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");


        int snakeLength = Integer.parseInt(scanner.nextLine());
        char[] currentSnake = new char[snakeLength];

        findSnakes(0, 0, 0, 'S', currentSnake, snakeLength);

        RESULT.append(String.format("Snakes count = %d", foundSnakes));

        System.out.println(RESULT);
    }

    private static void findSnakes(int index, int row, int col,
                                   char direction, char[] currentSnake, int snakeLength) {
        if (index >= snakeLength) {
            markSnake(new String(currentSnake));
        } else {
            String current = row + " " + col;

            if (VISITED.contains(current)) {
                return;
            }

            VISITED.add(current);

            currentSnake[index] = direction;

            findSnakes(index + 1, row, col + 1, 'R', currentSnake, snakeLength);
            findSnakes(index + 1, row + 1, col, 'D', currentSnake, snakeLength);
            findSnakes(index + 1, row, col - 1, 'L', currentSnake, snakeLength);
            findSnakes(index + 1, row - 1, col, 'U', currentSnake, snakeLength);

            VISITED.remove(current);
        }
    }

    private static void markSnake(String snake) {
        if (SNAKES.contains(snake)) {
            return;
        }

        RESULT.append(snake).append(System.lineSeparator());

        foundSnakes++;

        String flipped = flip(snake);
        String reversed = reverse(snake);
        String flippedReversed = reverse(flipped);

        for (int i = 0; i < 4; i++) {
            snake = rotate(snake);
            SNAKES.add(snake);
        }

        for (int i = 0; i < 4; i++) {
            flipped = rotate(flipped);
            SNAKES.add(flipped);
        }

        for (int i = 0; i < 4; i++) {
            reversed = rotate(reversed);
            SNAKES.add(reversed);
        }

        for (int i = 0; i < 4; i++) {
            flippedReversed = rotate(flippedReversed);
            SNAKES.add(flippedReversed);
        }
    }

    private static String rotate(String snake) {
        char[] rotated = new char[snake.length()];

        for (int i = 0; i < rotated.length; i++) {
            switch (snake.charAt(i)) {
            case 'R':
                rotated[i] = 'D';
                break;
            case 'D':
                rotated[i] = 'L';
                break;
            case 'L':
                rotated[i] = 'U';
                break;
            case 'U':
                rotated[i] = 'R';
                break;
            default:
                rotated[i] = snake.charAt(i);
                break;
            }
        }

        return new String(rotated);
    }

    private static String reverse(String snake) {
        char[] reversed = new char[snake.length()];

        reversed[0] = 'S';

        for (int i = reversed.length - 1; i >= 1; i--) {
            reversed[reversed.length - i] = snake.charAt(i);
        }

        return new String(reversed);
    }

    private static String flip(String snake) {
        char[] flipped = new char[snake.length()];

        for (int i = 0; i < flipped.length; i++) {
            switch (snake.charAt(i)) {
            case 'U':
                flipped[i] = 'D';
                break;
            case 'D':
                flipped[i] = 'U';
                break;
            default:
                flipped[i] = snake.charAt(i);
                break;
            }
        }

        return new String(flipped);
    }
}
