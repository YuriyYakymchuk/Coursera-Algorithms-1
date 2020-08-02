package com.algorithms.part.one.week.fourth.algorithms.queues;

public class Heap {

    public static void sort(Comparable[] array) {
        int size = array.length;
        for (int i = size/2; i >= 1; i--) {
            sink(array, i, size);
        }
        while (size > 1) {
            swap(array, 1, size);
            sink(array, 1, --size);
        }
    }

    private static void sink(Comparable[] array, int i, int size) {
        while (2 * i <= size) {
            int max = 2 * i;
            if (max < size && less(array, max, max + 1)) {
                max++;
            }
            if (!less(array, i, max)) {
                break;
            }
            swap(array, i, max);
            i = max;
        }
    }

    private static boolean less(Comparable[] array, int i, int j) {
        return array[i-1].compareTo(array[j-1]) < 0;
    }

    private static void swap(Comparable[] array, int i, int j) {
        Comparable swap = array[i-1];
        array[i-1] = array[j-1];
        array[j-1] = swap;
    }

}
