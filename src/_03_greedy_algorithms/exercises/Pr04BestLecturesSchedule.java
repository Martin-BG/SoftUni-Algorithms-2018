package _03_greedy_algorithms.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Pr04BestLecturesSchedule {

    private static final Charset ENCODING = Charset.forName("UTF-8");
    private static final String SEPARATORS = "[:-]";
    private static final String LECTURE_FORMAT = "%d-%d -> %s";
    private static final String LECTURES_VISITED = "Lectures (%d):%n";
    private static final int NAME_INDEX = 0;
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 2;
    private static final int SKIPPED_CHARS = "Lectures: ".length();

    public static void main(String[] args) {
        System.out.println(getScheduleString(getLectures()));
    }

    private static Queue<Lecture> getLectures() {
        Queue<Lecture> lectures = new PriorityQueue<>(Comparator.comparingInt(lecture -> lecture.end));

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, ENCODING))) {
            int lecturesCount = Integer.parseInt(reader.readLine().substring(SKIPPED_CHARS));
            while (lecturesCount-- > 0) {
                String[] lecture = reader.readLine().split(SEPARATORS);

                String name = lecture[NAME_INDEX].trim();
                int start = Integer.parseInt(lecture[START_INDEX].trim());
                int end = Integer.parseInt(lecture[END_INDEX].trim());

                lectures.add(new Lecture(name, start, end));
            }
        } catch (IOException | NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

        return lectures;
    }

    private static String getScheduleString(final Queue<Lecture> lectures) {
        StringBuilder sb = new StringBuilder();

        int lecturesVisited = 0;
        int lastFinishTime = 0;

        while (!lectures.isEmpty()) {
            Lecture lecture = lectures.remove();

            if (lecture.start >= lastFinishTime) {
                sb.append(String.format(LECTURE_FORMAT, lecture.start, lecture.end, lecture.name)).append(System.lineSeparator());
                lastFinishTime = lecture.end;
                lecturesVisited++;
            }
        }

        sb.insert(0, String.format(LECTURES_VISITED, lecturesVisited));

        return sb.toString();
    }

    private static class Lecture {

        private final String name;
        private final int start;
        private final int end;

        Lecture(final String name, final int start, final int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }
}
