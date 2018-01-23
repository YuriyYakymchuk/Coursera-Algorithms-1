package com.algorithms.week.second.sort;

public class SortHelper {

    public static boolean less (Comparable a, Comparable b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }
        return a.compareTo(b) < 0;
    }

    public static void exchange(Comparable[] array, int i, int j) {
        Comparable swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }
}
