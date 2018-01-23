package com.algorithms.week.third.quicksort;

import edu.princeton.cs.algs4.StdRandom;

import static com.algorithm.SortUtils.less;
import static com.algorithm.SortUtils.swap;

public class Main {

    public static void main(String[] args) {
        Integer[] array = buildArray(10);
        sort(array);
        printArray(array);
    }

    private static void sort(Comparable[] array) {
        threeWayQuickSort(array, 0, array.length - 1);
    }

    private static void threeWayQuickSort(Comparable[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        Comparable v = array[left];
        int i = left + 1;
        int lt = left;
        int gt = right;
        while (gt >= i) {
            int compareResult = array[i].compareTo(v);
            if (compareResult < 0) {
                swap(array, i, lt);
                i++;
                lt++;
            } else if (compareResult > 0) {
                swap(array, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        threeWayQuickSort(array, left, lt - 1);
        threeWayQuickSort(array, gt + 1, right);
    }

    private static Comparable quickSelection(Comparable[] array, int k) {
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int j = partition(array, low, high);
            if (j > k) {
                high = j - 1;
            } else if (j < k) {
                low = j + 1;
            } else {
                break;
            }
        }
        return array[k];
    }

    private static void quickSort(Comparable[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int j = partition(array, left, right);
        quickSort(array, left, j - 1);
        quickSort(array, j + 1, right);
    }

    private static int partition(Comparable[] array, int low, int high) {
        int i = low + 1;
        int j = high;
        while (true) {
            while (less(array[i], array[low]) && i < high) {
                i++;
            }
            while (less(array[low], array[j]) && j > low) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
            i++;
            j--;
        }
        swap(array, low, j);
        return j;
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
