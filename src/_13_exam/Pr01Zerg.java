package _13_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Pr01Zerg {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String[] tokens = reader.readLine().split("\\s+");
        final int rows = Integer.parseInt(tokens[0]);
        final int cols = Integer.parseInt(tokens[1]);

        BigInteger[][] dp = new BigInteger[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                dp[row][col] = BigInteger.ZERO;
            }
        }

        tokens = reader.readLine().split("\\s+");
        final int baseRow = Integer.parseInt(tokens[0]);
        final int baseCol = Integer.parseInt(tokens[1]);

        int enemiesCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < enemiesCount; i++) {
            tokens = reader.readLine().split("\\s+");
            final int row = Integer.parseInt(tokens[0]);
            final int col = Integer.parseInt(tokens[1]);
            dp[row][col] = null;
        }

        BigInteger paths = numberOfPaths(dp, rows, cols, baseRow, baseCol);

        System.out.println(paths);

    }

    static BigInteger numberOfPaths(BigInteger[][] dp, int rows, int cols, int baseRow, int baseCol) {

        dp[0][0] = BigInteger.ONE;

        for (int col = 1; col < cols; col++) {
            if (dp[0][col] == null) {
                break;
            }
            dp[0][col] = BigInteger.ONE;
        }

        for (int row = 1; row < rows; row++) {
            if (dp[row][0] == null) {
                break;
            }
            dp[row][0] = BigInteger.ONE;
        }

        for (int row = 1; row <= baseRow; row++) {
            for (int col = 1; col <= baseCol; col++) {
                if (dp[row][col] == null) {
                    continue;
                }
                final BigInteger upper = dp[row - 1][col] != null ? dp[row - 1][col] : BigInteger.ZERO;
                final BigInteger left = dp[row][col - 1] != null ? dp[row][col - 1] : BigInteger.ZERO;
                dp[row][col] = upper.add(left);
            }
        }

        return dp[baseRow][baseCol];
    }
}
