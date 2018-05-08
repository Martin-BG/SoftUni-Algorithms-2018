package _10_solving_practical_problems.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Pr03LumberSolution1 {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String[] tokens = reader.readLine().split("\\s+");
            int logsCount = Integer.parseInt(tokens[0]);
            int queries = Integer.parseInt(tokens[1]);

            List<Log> logs = new ArrayList<>();
            List<List<Integer>> graph = new ArrayList<>();
            graph.add(new ArrayList<>());
            for (int index = 1; index <= logsCount; index++) {
                tokens = reader.readLine().split("\\s+");
                int x1 = Integer.parseInt(tokens[0]);
                int y1 = Integer.parseInt(tokens[1]);
                int x2 = Integer.parseInt(tokens[2]);
                int y2 = Integer.parseInt(tokens[3]);

                Log currentLog = new Log(x1, y1, x2, y2, index);

                graph.add(new ArrayList<>());

                for (Log log : logs) {
                    if (!log.overlap(currentLog)) {
                        continue;
                    }

                    graph.get(log.index).add(index);
                    graph.get(index).add(log.index);
                }

                logs.add(currentLog);
            }

            boolean[] visited = new boolean[logsCount + 1];
            int[] id = new int[logsCount + 1];
            int count = 0;
            for (int i = 1; i <= logsCount; i++) {
                if (visited[i]) {
                    continue;
                }

                dfs(i, visited, id, graph, count);
                count++;
            }

            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < queries; i++) {
                tokens = reader.readLine().split("\\s+");
                int id1 = Integer.parseInt(tokens[0]);
                int id2 = Integer.parseInt(tokens[1]);

                sb.append(id[id1] == id[id2] ? "YES" : "NO").append(System.lineSeparator());
            }
            System.out.println(sb.toString());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int i, boolean[] visited, int[] id, List<List<Integer>> graph, int count) {
        visited[i] = true;
        id[i] = count;

        for (Integer child : graph.get(i)) {
            if (!visited[child]) {
                dfs(child, visited, id, graph, count);
            }
        }
    }

    private static class Log {
        private int index;
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        Log(int x1, int y1, int x2, int y2, int index) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;

            this.index = index;
        }

        boolean overlap(Log other) {
            return this.x1 <= other.x2 &&
                    other.x1 <= this.x2 &&
                    this.y1 >= other.y2 &&
                    other.y1 >= this.y2;
        }
    }
}
