package com.algorithms.part.one.week.third.mergesort;

import edu.princeton.cs.algs4.StdRandom;

import static com.algorithms.part.one.SortUtils.less;

public class Main {

    public static void main(String[] args) {
        Integer[] array = buildArray(10);
        sort(array);
        printArray(array);
    }

    private static void sort(Comparable[] array) {
        Integer[] temp = new Integer[array.length];
        mergeSort(array, temp, 0, array.length - 1);
    }

    private static void bottomMergeSort(Integer[] array) {
        int N = array.length;
        Integer[] temp = new Integer[N];
        for (int size = 1; size < N; size *= 2) {
            for (int low = 0; low < N - size; low += size + size) {
                merge(array, temp, low, low + size - 1, Math.min(low + size + size - 1, N - 1));
            }
        }
    }

    private static void mergeSort(Comparable[] array, Comparable[] temporary, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low)/2;
        mergeSort(array, temporary, low, mid);
        mergeSort(array, temporary, mid + 1, high);
        if (!less(array[mid + 1], array[mid])) {
            return;
        }
        merge(array, temporary, low, mid, high);
    }

    private static void merge(Comparable[] array, Comparable[] temp, int low, int mid, int high) {
        for (int i = low; i <= mid; i++) {
            temp[i] = array[i];
        }
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = array[j++];
            } else if (j > high) {
                array[k] = temp[i++];
            } else if (less(array[j], temp[i])) {
                array[k] = array[j++];
            } else {
                array[k] = temp[i++];
            }
        }
    }

    private static void printArray(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static Integer[] buildArray(int k) {
        Integer[] array = new Integer[k];
        for (int i = 0; i < k; i++) {
            array[i] = StdRandom.uniform(10);
        }
        return array;
    }
}
