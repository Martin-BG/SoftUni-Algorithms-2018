package _13_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Pr02Protoss {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int protossCount = Integer.parseInt(reader.readLine());

        Map<Integer, Set<Integer>> connections = new HashMap<>();

        for (int i = 0; i < protossCount; i++) {
            Set<Integer> connected = new HashSet<>();
            char[] links = reader.readLine().toCharArray();
            for (int j = 0; j < links.length; j++) {
                if (links[j] == 'Y') {
                    connected.add(j);
                }
            }
            connections.put(i, connected);
        }

        int max = getMaxHyperConnections(protossCount, connections);

        System.out.println(max);
    }

    private static int getMaxHyperConnections(final int protossCount,
                                              final Map<Integer, Set<Integer>> connections) {
        int max = 0;

        for (int i = 0; i < protossCount; i++) {
            int current = getHyperConnections(i, protossCount, connections);
            if (current > max) {
                max = current;
            }
        }

        return max;
    }

    private static int getHyperConnections(final int parent,
                                           final int protossCount,
                                           final Map<Integer, Set<Integer>> connections) {
        int hiperConnections = 0;
        boolean[] visited = new boolean[protossCount];
        visited[parent] = true;

        Deque<Integer> queue = new ArrayDeque<>(connections.get(parent));
        for (final Integer child : connections.get(parent)) {
            queue.addAll(connections.get(child));
        }

        Integer current;
        while ((current = queue.pollFirst()) != null) {
            if (!visited[current]) {
                visited[current] = true;
                hiperConnections++;
            }
        }

        return hiperConnections;
    }
}
