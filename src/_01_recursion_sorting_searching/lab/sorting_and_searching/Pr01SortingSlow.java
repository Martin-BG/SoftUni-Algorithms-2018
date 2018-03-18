package _01_recursion_sorting_searching.lab.sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Pr01SortingSlow {

    public static void main(String[] args) {
        Integer[] integers = readIntegers();

//        shuffleFisherYatesSattolo(integers);

//        Arrays.sort(integers);

//        selectionSort(integers);
//        bubbleSort(integers);
//        bubbleSortOptimized(integers);

        insertionSort(integers);

        printArray(integers);
    }

    // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    private static <T extends Comparable> void shuffleFisherYatesSattolo(T[] array) {
        Random random = new Random();
        int index = array.length;

        while (index > 1) {
            index--;
            int swapIndex = random.nextInt(index); // [0, index - 1]
            swap(array, index, swapIndex);
        }
    }

    // https://en.wikipedia.org/wiki/Insertion_sort
    private static <T extends Comparable> void insertionSort(T[] array) {
        int lastSortedIndex = 0;

        while (lastSortedIndex < array.length - 1) {
            int indexSelected = lastSortedIndex + 1;

            for (int i = lastSortedIndex; i >= 0; i--) {
                if (isLess(array[i], array[indexSelected])) {
                    swap(array, indexSelected, i);
                    indexSelected = i;
                }
            }

            lastSortedIndex++;
        }
    }

    // https://en.wikipedia.org/wiki/Bubble_sort
    private static <T extends Comparable> void bubbleSortOptimized(T[] array) {
        boolean swapped;
        int limit = array.length;
        do {
            swapped = false;
            limit--;

            for (int i = 0; i < limit; i++) {
                if (isLess(array[i], array[i + 1])) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }

        } while (swapped);
    }

    // https://en.wikipedia.org/wiki/Bubble_sort
    private static <T extends Comparable> void bubbleSort(T[] array) {
        boolean swapped;
        do {
            swapped = false;

            for (int i = 0; i < array.length - 1; i++) {
                if (isLess(array[i], array[i + 1])) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }

        } while (swapped);
    }

    // https://en.wikipedia.org/wiki/Selection_sort
    private static <T extends Comparable> void selectionSort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int min_index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (isLess(array[min_index], array[j])) {
                    min_index = j;
                }
            }
            swap(array, i, min_index);
        }
    }

    private static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable> boolean isLess(T first, T second) {
        return first.compareTo(second) > 0;
    }

    private static Integer[] readIntegers() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return Arrays
                    .stream(reader.readLine().trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return new Integer[]{};
    }

    private static <T> void printArray(T[] array) {
        System.out.println(Arrays
                .toString(array)
                .replaceAll("[,\\[\\]]", ""));
    }
}
