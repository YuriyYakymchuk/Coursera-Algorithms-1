package com.algorithms.part.one;

public class SortUtils {

    public static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }
}
