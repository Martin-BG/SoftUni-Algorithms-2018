package _12_exam_preparation.exam_2017_08_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Pr04ChainLightningSlow {

    private static final String TOKENS_SEPARATOR = "\\s+";

    public static void main(final String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final int neighbourhoodCount = Integer.parseInt(reader.readLine());
        final int connectionsCount = Integer.parseInt(reader.readLine());
        final int lightningsCount = Integer.parseInt(reader.readLine());

        final Neighbourhood[] neighbourhoods = createNeighbourhoods(neighbourhoodCount);

        parseConnections(reader, connectionsCount, neighbourhoods);

        for (int i = 0; i < lightningsCount; i++) {
            final String[] tokens = reader.readLine().split(TOKENS_SEPARATOR);
            final int neighbourhoodId = Integer.parseInt(tokens[0]);
            final int initialDamage = Integer.parseInt(tokens[1]);

            Carrier carrier = new Carrier(neighbourhoodId, 0);
            carrier.damage = initialDamage;

            final Queue<Carrier> queue = new PriorityQueue<>();
            final boolean[] visited = new boolean[neighbourhoodCount];

            queue.add(carrier);

            while ((carrier = queue.poll()) != null) {
                if (visited[carrier.target]) {
                    continue;
                }

                neighbourhoods[carrier.target].totalDamage += carrier.damage;
                visited[carrier.target] = true;

                final int damage = carrier.damage / 2;

                neighbourhoods[carrier.target].neighbours.forEach(c -> {
                    c.damage = damage;
                    queue.add(c);
                });
            }
        }

        int maxDamage = neighbourhoods[0].totalDamage;
        for (int i = 1; i < neighbourhoodCount; i++) {
            if (maxDamage < neighbourhoods[i].totalDamage) {
                maxDamage = neighbourhoods[i].totalDamage;
            }
        }

        System.out.println(maxDamage);
    }

    private static void parseConnections(final BufferedReader reader,
                                         final int connectionsCount,
                                         final Neighbourhood[] neighbourhoods) throws IOException {
        for (int i = 0; i < connectionsCount; i++) {
            final String[] tokens = reader.readLine().split(TOKENS_SEPARATOR);
            final int from = Integer.parseInt(tokens[0]);
            final int to = Integer.parseInt(tokens[1]);
            final int distance = Integer.parseInt(tokens[2]);

            neighbourhoods[from].neighbours.add(new Carrier(to, distance));
            neighbourhoods[to].neighbours.add((new Carrier(from, distance)));
        }
    }

    private static Neighbourhood[] createNeighbourhoods(final int neighbourhoodCount) {
        final Neighbourhood[] neighbourhoods = new Neighbourhood[neighbourhoodCount];

        for (int i = 0; i < neighbourhoodCount; i++) {
            neighbourhoods[i] = new Neighbourhood();
        }
        return neighbourhoods;
    }

    private static class Neighbourhood {
        private final Set<Carrier> neighbours;
        private int totalDamage;

        Neighbourhood() {
            this.neighbours = new TreeSet<>();
        }
    }

    private static class Carrier implements Comparable<Carrier> {
        private final int target;
        private final int distance;
        private int damage;

        Carrier(final int target, final int distance) {
            this.target = target;
            this.distance = distance;
        }

        @Override
        public int compareTo(final Carrier carrier) {
            return Integer.compare(this.distance, carrier.distance);
        }
    }
}
