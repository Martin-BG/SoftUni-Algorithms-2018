package _12_exam_preparation.exam_2017_08_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Pr03TourDeSofia {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final int verticesCount = Integer.parseInt(reader.readLine());
        final int edgesCount = Integer.parseInt(reader.readLine());
        final int startVertex = Integer.parseInt(reader.readLine());

        final Map<Integer, Set<Integer>> edges = parseEdges(reader, edgesCount, verticesCount);

        final int result = BFS(startVertex, verticesCount, edges);

        System.out.println(result);
    }

    private static Map<Integer, Set<Integer>> parseEdges(final BufferedReader reader,
                                                         final int edgesCount,
                                                         final int verticesCount) throws IOException {
        final Map<Integer, Set<Integer>> edges = new HashMap<>();
        for (int i = 0; i < verticesCount; i++) {
            edges.put(i, new HashSet<>());
        }

        for (int i = 0; i < edgesCount; i++) {
            final String[] tokens = reader.readLine().split("\\s+");
            final int from = Integer.parseInt(tokens[0]);
            final int to = Integer.parseInt(tokens[1]);

            edges.get(from).add(to);
        }

        return edges;
    }

    private static int BFS(final int start,
                           final int verticesCount,
                           final Map<Integer, Set<Integer>> edges) {

        final Integer[] distances = new Integer[verticesCount];
        final Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        distances[start] = 0;

        int visited = 0;
        Integer from;
        while ((from = queue.poll()) != null) {
            visited++;
            final Integer distance = distances[from] + 1;

            for (final Integer to : edges.get(from)) {
                if (to == start) {
                    return distance;
                }

                if (distances[to] == null) {
                    distances[to] = distance;
                    queue.add(to);
                }
            }
        }

        return visited;
    }
}
