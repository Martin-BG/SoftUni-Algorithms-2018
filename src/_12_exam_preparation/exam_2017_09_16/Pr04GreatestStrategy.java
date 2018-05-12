package _12_exam_preparation.exam_2017_09_16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pr04GreatestStrategy {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String[] tokens = reader.readLine().split("\\s+");
        final int areasCount = Integer.parseInt(tokens[0]);
        final int connectionsCount = Integer.parseInt(tokens[1]);
        final int startArea = Integer.parseInt(tokens[2]);

        final Map<Integer, Set<Integer>> graph = parseConnections(reader, areasCount, connectionsCount);

        dfs(startArea, graph, new HashSet<>());

        final int maxValue = getMaxValue(areasCount, graph);

        System.out.println(maxValue);
    }

    private static int getMaxValue(final int areasCount, final Map<Integer, Set<Integer>> graph) {
        int maxValue = 0;
        final Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= areasCount; i++) {
            if (!visited.contains(i)) {
                final int value = getAreaValue(i, graph, visited);
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }
        return maxValue;
    }

    private static Map<Integer, Set<Integer>> parseConnections(final BufferedReader reader,
                                                               final int areasCount,
                                                               final int connectionsCount) throws IOException {
        final Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= areasCount; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < connectionsCount; i++) {
            final String[] tokens = reader.readLine().split("\\s+");
            final int from = Integer.parseInt(tokens[0]);
            final int to = Integer.parseInt(tokens[1]);

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        return graph;
    }

    private static int getAreaValue(final int parent,
                                    final Map<Integer, Set<Integer>> graph,
                                    final Set<Integer> visited) {
        int value = parent;
        visited.add(parent);

        for (final int child : graph.get(parent)) {
            if (!visited.contains(child)) {
                value += getAreaValue(child, graph, visited);
            }
        }

        return value;
    }

    private static int dfs(final int parent,
                           final Map<Integer, Set<Integer>> graph,
                           final Set<Integer> visited) {
        visited.add(parent);

        Set<Integer> toRemove = new HashSet<>();

        int areasCount = 1;
        for (final Integer child : graph.get(parent)) {
            if (!visited.contains(child)) {
                int subTreeSize = dfs(child, graph, visited);

                if (subTreeSize % 2 == 0) {
                    toRemove.add(child);
                }

                areasCount += subTreeSize;
            }
        }

        for (final Integer child : toRemove) {
            graph.get(parent).remove(child);
            graph.get(child).remove(parent);
        }

        return areasCount;
    }
}
