package _02_combinatorial_algorithms.exercises;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pr05Cubes {

    private static Set<Cube> isomorphicCubes;
    private static int[] currentEdges;
    private static int[] colorsLeftCount;
    private static int cubesCount;

    public static void main(String[] args) {
        int[] sticks = readInput();
        System.out.println(numberOfCubes(sticks));
    }

    private static void generateCubes(int edgeIndex) {
        if (edgeIndex == Cube.EDGE_COUNT) {
            Cube cube = new Cube(currentEdges);
            if (isomorphicCubes.contains(cube)) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                cube.rotateXY();
                for (int j = 0; j < 4; j++) {
                    cube.rotateXZ();
                    for (int k = 0; k < 4; k++) {
                        cube.rotateYZ();
                        isomorphicCubes.add(new Cube(cube));
                    }
                }
            }

            cubesCount++;
            return;
        }

        for (int color = 1; color <= Cube.MAX_COLOR; color++) {
            if (colorsLeftCount[color] > 0) {
                colorsLeftCount[color]--;
                currentEdges[edgeIndex] = color;
                generateCubes(edgeIndex + 1);
                colorsLeftCount[color]++;
            }
        }
    }

    private static int numberOfCubes(int[] sticks) {
        colorsLeftCount = new int[Cube.MAX_COLOR + 1];
        for (int i = 0; i < Cube.EDGE_COUNT; i++) {
            colorsLeftCount[sticks[i]]++;
        }

        currentEdges = new int[Cube.EDGE_COUNT];
        isomorphicCubes = new HashSet<>();
        cubesCount = 0;
        generateCubes(0);

        return cubesCount;
    }

    private static int[] readInput() {
        int[] sticks = new int[Cube.EDGE_COUNT];
        Scanner scanner = new Scanner(System.in, "UTF-8");
        String[] sticksString = scanner.nextLine().split("\\s+");
        for (int i = 0; i < Cube.EDGE_COUNT; i++) {
            sticks[i] = Integer.parseInt(sticksString[i]);
        }

        return sticks;
    }

    private static class Cube {
        static final int EDGE_COUNT = 12;
        static final int MAX_COLOR = 6;
        private int[] edges;

        Cube() {
            edges = new int[EDGE_COUNT];
        }

        Cube(int[] newEdges) {
            this();
            System.arraycopy(newEdges, 0, this.edges, 0, EDGE_COUNT);
        }

        Cube(Cube cube) {
            this();
            System.arraycopy(cube.edges, 0, this.edges, 0, EDGE_COUNT);
        }

        void rotateXY() {
            edges = new int[]{
                    edges[1], edges[2], edges[3], edges[0],
                    edges[5], edges[6], edges[7], edges[4],
                    edges[9], edges[10], edges[11], edges[8]
            };
        }

        void rotateXZ() {
            edges = new int[]{
                    edges[4], edges[9], edges[5], edges[1],
                    edges[8], edges[10], edges[2], edges[0],
                    edges[7], edges[11], edges[6], edges[3]
            };
        }

        void rotateYZ() {
            edges = new int[]{
                    edges[2], edges[5], edges[10], edges[6],
                    edges[1], edges[9], edges[11], edges[3],
                    edges[0], edges[4], edges[8], edges[7]
            };
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Cube cube = (Cube) o;

            return Arrays.equals(edges, cube.edges);
        }

        @Override
        public int hashCode() {
            int result = EDGE_COUNT;
            result = 31 * result + MAX_COLOR;
            result = 31 * result + Arrays.hashCode(edges);
            return result;
        }
    }
}
