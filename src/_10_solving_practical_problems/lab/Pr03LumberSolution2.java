package _10_solving_practical_problems.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class Pr03LumberSolution2 {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String[] tokens = reader.readLine().split("\\s+");
            int logsCount = Integer.parseInt(tokens[0]);
            int queries = Integer.parseInt(tokens[1]);

            Log[] logs = new Log[logsCount + 1];
            for (int root = 1; root <= logsCount; root++) {
                tokens = reader.readLine().split("\\s+");
                int x1 = Integer.parseInt(tokens[0]);
                int y1 = Integer.parseInt(tokens[1]);
                int x2 = Integer.parseInt(tokens[2]);
                int y2 = Integer.parseInt(tokens[3]);

                Log currentLog = new Log(x1, y1, x2, y2, root);
                logs[root] = currentLog;

                Set<Integer> toRedirect = new HashSet<>();
                for (int j = 1; j < root; j++) {
                    if (!logs[j].isOverlapping(currentLog)) {
                        continue;
                    }

                    toRedirect.add(logs[j].root);
                }

                for (int j = 1; j < root; j++) {
                    if (toRedirect.contains(logs[j].root)) {
                        logs[j].root = root;
                    }
                }
            }

            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < queries; i++) {
                tokens = reader.readLine().split("\\s+");
                int id1 = Integer.parseInt(tokens[0]);
                int id2 = Integer.parseInt(tokens[1]);

                sb.append(logs[id1].isConnected(logs[id2]) ? "YES" : "NO").append(System.lineSeparator());
            }
            System.out.println(sb.toString());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static class Log {
        private int root;
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        Log(int x1, int y1, int x2, int y2, int root) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.root = root;
        }

        boolean isOverlapping(Log other) {
            return this.x1 <= other.x2 &&
                    other.x1 <= this.x2 &&
                    this.y1 >= other.y2 &&
                    other.y1 >= this.y2;
        }

        boolean isConnected(Log other) {
            return this.root == other.root;
        }
    }
}
