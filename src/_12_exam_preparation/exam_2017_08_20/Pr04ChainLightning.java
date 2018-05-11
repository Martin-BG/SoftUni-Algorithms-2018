package _12_exam_preparation.exam_2017_08_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pr04ChainLightning {

    private static final String TOKENS_SEPARATOR = "\\s+";

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final int verticesCount = Integer.parseInt(reader.readLine());
        final int edgesCount = Integer.parseInt(reader.readLine());
        final int lightningsCount = Integer.parseInt(reader.readLine());

        final List<Edge> edges = getEdges(reader, edgesCount);
        final List<Edge> spanningTree = kruskal(verticesCount, edges);
        final List<List<Integer>> graph = getGraph(verticesCount, spanningTree);

        final int[] vertexDamage = takeDamage(reader, verticesCount, lightningsCount, graph);

        System.out.println(getMaxDamage(vertexDamage));
    }

    private static int[] takeDamage(final BufferedReader reader,
                                    final int verticesCount,
                                    final int lightningsCount,
                                    final List<List<Integer>> graph) throws IOException {

        final int[] vertexDamage = new int[verticesCount];

        for (int i = 0; i < lightningsCount; i++) {
            final String[] tokens = reader.readLine().split(TOKENS_SEPARATOR);

            final int target = Integer.parseInt(tokens[0]);
            final int damage = Integer.parseInt(tokens[1]);
            final boolean[] visited = new boolean[verticesCount];

            DFS(target, damage, vertexDamage, visited, graph);
        }

        return vertexDamage;
    }

    private static int getMaxDamage(final int[] vertexDamage) {
        int maxDamage = 0;

        for (int damage : vertexDamage) {
            if (damage > maxDamage) {
                maxDamage = damage;
            }
        }

        return maxDamage;
    }

    private static void DFS(final int target,
                            int damage,
                            final int[] vertexDamage,
                            final boolean[] visited,
                            final List<List<Integer>> graph) {

        if (visited[target]) {
            return;
        }

        visited[target] = true;
        vertexDamage[target] += damage;

        damage /= 2;

        if (damage == 0) {
            return;
        }

        for (Integer child : graph.get(target)) {
            DFS(child, damage, vertexDamage, visited, graph);
        }
    }

    private static List<List<Integer>> getGraph(final int verticesCount,
                                                final List<Edge> edges) {

        final List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < verticesCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (final Edge edge : edges) {
            graph.get(edge.from).add(edge.to);
            graph.get(edge.to).add(edge.from);
        }

        return graph;
    }

    private static List<Edge> getEdges(final BufferedReader reader,
                                       final int edgesCount) throws IOException {

        final List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            final String[] tokens = reader.readLine().split(TOKENS_SEPARATOR);

            final int from = Integer.parseInt(tokens[0]);
            final int to = Integer.parseInt(tokens[1]);
            final int distance = Integer.parseInt(tokens[2]);

            edges.add(new Edge(from, to, distance));
        }

        return edges;
    }

    private static List<Edge> kruskal(final int numberOfVertices,
                                      final List<Edge> edges) {

        edges.sort(Comparator.naturalOrder());

        final int[] parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }


        final List<Edge> spanningTree = new ArrayList<>();

        for (final Edge edge : edges) {
            final int startVertexRoot = findRoot(edge.from, parent);
            final int endVertexRoot = findRoot(edge.to, parent);

            if (startVertexRoot != endVertexRoot) {
                spanningTree.add(edge);
                parent[startVertexRoot] = endVertexRoot;
            }
        }

        return spanningTree;
    }

    private static int findRoot(int vertex,
                                final int[] parent) {

        int root = vertex;

        while (parent[root] != root) {
            root = parent[root];
        }

        while (vertex != root) {
            int oldParent = parent[vertex];
            parent[vertex] = root;
            vertex = oldParent;
        }

        return root;
    }

    private static class Edge implements Comparable<Edge> {

        private final int from;
        private final int to;
        private final int distance;

        Edge(final int from, final int to, final int weight) {
            this.from = from;
            this.to = to;
            this.distance = weight;
        }

        @Override
        public int compareTo(final Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
