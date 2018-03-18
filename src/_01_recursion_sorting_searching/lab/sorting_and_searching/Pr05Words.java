package _01_recursion_sorting_searching.lab.sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr05Words {

    private static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] chars = reader.readLine().trim().toCharArray();
        Arrays.sort(chars);
        permuteRep(chars, 0, chars.length - 1);

        System.out.println(count);
    }

    private static void permuteRep(char[] chars, int start, int end) {

        boolean match = true;
        char prev = ' ';
        for (char aChar : chars) {
            if (prev == aChar) {
                match = false;
                break;
            }
            prev = aChar;
        }

        if (match) {
            count++;
        }

        if (start >= end) {
            return;
        }

        for (int left = end - 1; left >= start; left--) {
            for (int right = left + 1; right <= end; right++) {
                if (chars[left] != chars[right]) {
                    char temp = chars[left];
                    chars[left] = chars[right];
                    chars[right] = temp;
                    permuteRep(chars, left + 1, end);
                }
            }
            char firstElement = chars[left];
            System.arraycopy(chars, left + 1, chars, left, end - left);
            chars[end] = firstElement;
        }
    }
}
