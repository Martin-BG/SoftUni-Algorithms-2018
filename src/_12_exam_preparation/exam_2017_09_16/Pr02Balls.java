package _12_exam_preparation.exam_2017_09_16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Pr02Balls {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final int pocketsCount = Integer.parseInt(reader.readLine());
        final int ballsCount = Integer.parseInt(reader.readLine());
        final int pocketCapacity = Integer.parseInt(reader.readLine());

        final int[] pockets = new int[pocketsCount];
        final StringBuilder sb = new StringBuilder();

        gen(0, ballsCount, pockets, pocketCapacity, sb);

        System.out.println(sb.toString().trim());
    }

    private static void gen(final int pocket,
                            final int balls,
                            final int[] pockets,
                            final int pocketCapacity,
                            final StringBuilder sb) {
        if (pocket >= pockets.length) {
            if (balls == 0) {
                storeResult(pockets, sb);
            }
            return;
        }

        for (int currentBalls = pocketCapacity; currentBalls > 0; currentBalls--) {
            pockets[pocket] = currentBalls;
            gen(pocket + 1, balls - currentBalls, pockets, pocketCapacity, sb);
        }
    }

    private static void storeResult(final int[] pockets, final StringBuilder sb) {
        for (int num : pockets) {
            sb.append(num).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(System.lineSeparator());
    }
}
