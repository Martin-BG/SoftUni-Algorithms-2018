package _12_exam_preparation.exam_2017_09_16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pr03BlackMessup {

    public static void main(String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final int atomsCount = Integer.parseInt(reader.readLine());
        final int connectionsCount = Integer.parseInt(reader.readLine());

        final Map<Integer, List<Integer>> connections = new HashMap<>();
        final Map<String, Integer> indexToName = new HashMap<>();
        final Atom[] atoms = new Atom[atomsCount];
        for (int i = 0; i < atomsCount; i++) {
            final String[] tokens = reader.readLine().split("\\s+");
            final String name = tokens[0];
            final int mass = Integer.parseInt(tokens[1]);
            final int decay = Integer.parseInt(tokens[2]);

            indexToName.put(name, i);
            atoms[i] = new Atom(mass, decay);

            connections.put(i, new ArrayList<>());
        }

        for (int i = 0; i < connectionsCount; i++) {
            final String[] tokens = reader.readLine().split("\\s+");
            final int from = indexToName.get(tokens[0]);
            final int to = indexToName.get(tokens[1]);
            connections.get(from).add(to);
            connections.get(to).add(from);
        }

        final List<List<Atom>> molecules = findConnectedComponents(connections, atoms, atomsCount);

        final int maxValue = findStrongestMolecule(molecules);

        System.out.println(maxValue);
    }

    private static int findStrongestMolecule(final List<List<Atom>> molecules) {
        int maxValue = 0;
        for (final List<Atom> molecule : molecules) {
            int value = getMoleculeValue(molecule);
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    private static int getMoleculeValue(final List<Atom> molecule) {
        int value = 0;
        molecule.sort((a, b) -> b.mass - a.mass);
        int maxExpiration = 1;
        int usedAtoms = 0;
        for (final Atom atom : molecule) {
            if (atom.decay > maxExpiration) {
                usedAtoms++;
                value += atom.mass;
                maxExpiration = atom.decay;
            } else if (maxExpiration > usedAtoms) {
                usedAtoms++;
                value += atom.mass;
            }
        }
        return value;
    }

    private static List<List<Atom>> findConnectedComponents(final Map<Integer, List<Integer>> connections,
                                                            final Atom[] atoms,
                                                            final int atomsCount) {
        List<List<Atom>> components = new ArrayList<>();
        boolean[] visited = new boolean[atomsCount];

        for (Integer atomIndex : connections.keySet()) {
            if (!visited[atomIndex]) {
                List<Atom> component = new ArrayList<>();
                DFS(atomIndex, component, visited, atoms, connections);
                components.add(component);
            }
        }

        for (int i = 0; i < atomsCount; i++) {
            if (!visited[i]) {
                List<Atom> component = new ArrayList<>();
                component.add(atoms[i]);
                components.add(component);
            }
        }

        return components;
    }

    private static void DFS(final int atomIndex,
                            final List<Atom> component,
                            boolean[] visited,
                            final Atom[] atoms,
                            final Map<Integer, List<Integer>> connections) {
        visited[atomIndex] = true;
        component.add(atoms[atomIndex]);
        for (Integer childIndex : connections.get(atomIndex)) {
            if (!visited[childIndex]) {
                DFS(childIndex, component, visited, atoms, connections);
            }
        }
    }

    private static class Atom {
        private final int mass;
        private final int decay;

        private Atom(final int mass, final int decay) {
            this.mass = mass;
            this.decay = decay;
        }
    }
}
