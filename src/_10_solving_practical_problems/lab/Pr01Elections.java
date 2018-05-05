package _10_solving_practical_problems.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Pr01Elections {

    public static void main(final String[] args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int neededSeats = Integer.parseInt(reader.readLine());
            int parties = Integer.parseInt(reader.readLine());
            int[] votes = new int[parties];

            int maxVotes = 0;
            for (int i = 0; i < parties; i++) {
                votes[i] = Integer.parseInt(reader.readLine());
                maxVotes += votes[i];
            }

            Arrays.sort(votes);

            BigInteger[] votesDp = new BigInteger[maxVotes + 1];
            votesDp[0] = BigInteger.ONE;

            int mostSeats = 0;

            for (int partyVotes : votes) {
                for (int j = mostSeats + partyVotes; j >= partyVotes; j--) {
                    if (votesDp[j - partyVotes] == null) {
                        continue;
                    }

                    if (mostSeats < j) {
                        mostSeats = j;
                    }

                    if (votesDp[j] == null) {
                        votesDp[j] = votesDp[j - partyVotes];
                    } else {
                        votesDp[j] = votesDp[j].add(votesDp[j - partyVotes]);
                    }
                }
            }

            BigInteger combinations = BigInteger.ZERO;

            for (int i = neededSeats; i <= maxVotes; i++) {
                if (votesDp[i] == null) {
                    continue;
                }

                combinations = combinations.add(votesDp[i]);
            }

            System.out.println(combinations);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
