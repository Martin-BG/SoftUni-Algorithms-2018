package _12_exam_preparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Pr02TravellingPoliceman {

    public static void main(final String[] args) throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int fuel = Integer.parseInt(reader.readLine());

        final List<Street> streets = new ArrayList<>();
        String line;
        while (!"End".equals(line = reader.readLine())) {
            String[] tokens = line.split(", ");

            String name = tokens[0];
            int damage = Integer.parseInt(tokens[1]);
            int pokemonCount = Integer.parseInt(tokens[2]);
            int length = Integer.parseInt(tokens[3]);

            final Street street = new Street(name, damage, pokemonCount, length);

            if (street.value <= 0 || street.length > fuel) {
                continue;
            }

            streets.add(street);
        }

        final int streetsCount = streets.size();

        final int[][] dp = new int[streetsCount + 1][fuel + 1];

        int row = 1;
        for (Street street : streets) {
            System.arraycopy(dp[row - 1], 0, dp[row], 0, street.length);

            for (int col = street.length; col <= fuel; col++) {
                dp[row][col] = Math.max(dp[row - 1][col], dp[row - 1][col - street.length] + street.value);
            }

            row++;
        }

        final Set<Street> visited = new TreeSet<>(Comparator.comparingInt(street -> street.index));

        row = streetsCount;
        int col = fuel;
        while (dp[row][col] != 0) {
            if (dp[row - 1][col] == dp[row][col]) {
                row--;
            } else if (dp[row][col - 1] == dp[row][col]) {
                col--;
            } else {
                row--;
                final Street street = streets.get(row);
                col -= street.length;
                visited.add(street);
            }
        }

        int pokemonCaught = 0;
        int totalCarDamage = 0;
        int fuelLeft = fuel;

        final StringBuilder sb = new StringBuilder();

        for (Street street : visited) {
            sb.append(street.name).append(" -> ");
            pokemonCaught += street.pokemonCount;
            totalCarDamage += street.damage;
            fuelLeft -= street.length;
        }

        if (!visited.isEmpty()) {
            sb.delete(sb.length() - 4, sb.length());
        }

        sb.append(System.lineSeparator())
                .append("Total pokemons caught -> ").append(pokemonCaught)
                .append(System.lineSeparator())
                .append("Total car damage -> ").append(totalCarDamage)
                .append(System.lineSeparator())
                .append("Fuel Left -> ").append(fuelLeft);

        System.out.println(sb);
    }

    private static class Street {
        private static final int POKEMON_VALUE = 10;

        private static int counter = 0;

        private String name;
        private int damage;
        private int pokemonCount;
        private int length;
        private int value;
        private int index;

        Street(final String name, final int damage, final int pokemonCount, final int length) {
            this.name = name;
            this.damage = damage;
            this.pokemonCount = pokemonCount;
            this.length = length;
            this.value = this.pokemonCount * POKEMON_VALUE - this.damage;
            this.index = counter++;
        }

        @Override
        public String toString() {
            return String.format("Street{name='%s', damage=%d, pokemonCount=%d, length=%d, value=%d, index=%d}",
                    this.name, this.damage, this.pokemonCount, this.length, this.value, this.index);
        }
    }
}
