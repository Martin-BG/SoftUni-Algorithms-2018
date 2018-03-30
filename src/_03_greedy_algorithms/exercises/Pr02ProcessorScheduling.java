package _03_greedy_algorithms.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Pr02ProcessorScheduling {

    private static final int VALUE = 0;
    private static final int DEADLINE = 1;
    private static final int INDEX = 2;
    private static final Charset ENCODING = Charset.forName("UTF-8");
    private static final String ITEMS_SEPARATOR = " - ";
    private static final String RESULTS_SEPARATOR = " -> ";
    private static final String OPTIMAL_SCHEDULE = "Optimal schedule: ";
    private static final String TOTAL_VALUE = "Total value: ";

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {
            int tasksCount = Integer.parseInt(reader.readLine().substring(7));

            List<List<Integer>> tasks = new ArrayList<>();

            int currentIndex = 0;
            do {    // task data -> [VALUE, DEADLINE, INDEX(1, 2...)]
                tasks.add(Arrays.stream(reader.readLine().split(ITEMS_SEPARATOR))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()));
                tasks.get(currentIndex).add(++currentIndex);
            } while (currentIndex < tasksCount);

            tasks.sort((a, b) -> {  // first by VALUE DESC, then by DEADLINE ASC
                int cmp = b.get(VALUE) - a.get(VALUE);
                if (cmp == 0) {
                    cmp = a.get(DEADLINE) - b.get(DEADLINE);
                }
                return cmp;
            });

            int maxDeadLine = tasks.stream()
                    .map(a -> a.get(DEADLINE))
                    .max(Comparator.naturalOrder())
                    .orElse(0);

            boolean[] slots = new boolean[maxDeadLine];

            List<List<Integer>> result = new ArrayList<>();

            // Puts most valued tasks on the latest possible slots
            for (final List<Integer> task : tasks) {
                for (int i = task.get(DEADLINE) - 1; i >= 0; i--) {
                    if (slots[i]) {
                        continue;
                    }

                    slots[i] = true;
                    result.add(task);
                    break;
                }
            }

            // Prepare result for output
            final int[] totalValue = new int[1];
            List<Integer> executedTasks = result
                    .stream()
                    .sorted((a, b) -> { // first by DEADLINE ASC, then by VALUE DESC
                        int cmp = a.get(DEADLINE) - b.get(DEADLINE);
                        if (cmp == 0) {
                            cmp = b.get(VALUE) - a.get(VALUE);
                        }
                        return cmp;
                    })
                    .map(task -> {
                        totalValue[0] += task.get(VALUE);
                        return task.get(INDEX);
                    })
                    .collect(Collectors.toList());

            StringBuilder sb = new StringBuilder(OPTIMAL_SCHEDULE)
                    .append(executedTasks.toString()
                            .replaceAll("\\[", "")
                            .replaceAll("]", "")
                            .replaceAll(", ", RESULTS_SEPARATOR))
                    .append(System.lineSeparator())
                    .append(TOTAL_VALUE)
                    .append(totalValue[0]);
            System.out.println(sb);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
